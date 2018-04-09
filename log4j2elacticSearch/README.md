log4j2 发送日志到es中 
有两种方案 
1: 使用socketAppender 通过tcp协议 发送 功能多  需要自己构建 es能够识别的 数据流 
2: 自定义 appender  使用es client 发送  格式比较标准 简单  容易理解  需要自行构建 失败重试  之类的工作   
3:使用开源的第三方jar 

#### 自定义log4j2 appender 发送到es
###### 思路:
实现log4j2的 esAppender 借助 es highLevelClient 发送到 es中    
不使用 socketAppender 因为要适配es的tcp接口比较麻烦  不如直接使用es提供的客户端 
如果是发送到logstash 可以使用 socketAppender 
不使用transportClient 是因为 
第一 es官方说明 即将放弃这个客户端 
第二 他是基于netty实现的 如果项目没有使用netty 需要引入大量netty依赖 

#### 实例实现 
要求:
jdk8
es+kibana 6.2
docker
 
github上大神的实现版本:net.inemar.utility.log4j2elastic  这个是自己实现队列+单线程消费的模式  
由于版本什么的 不太适合 我自己按照自己的习惯写出一套基于jdk的多线程+队列的模式 
生产环境 建议 使用中间件级别的mq 替换 线程池和队列 采用异步消息同步方法模式去做 用mq保证消息的不丢失  

实现功能  基于jdk的线程池和队列 实现的一个异步消费  如果有现成的mq  建议 直接使用现成的mq 而不是自行构建系统内部队列和多线程消费 
0:启动es +kibana
这里采用 docker 启动  
```
docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana
```
访问 http://<ip>:5601 即可访问kibana    
1：依赖
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ming</groupId>
    <artifactId>log4j2-elacticSearch</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <spring.boot.version>1.5.10.RELEASE</spring.boot.version>
        <es.version>6.2.3</es.version>
    </properties>


    <!--仓库 如果需要区分仓库优先级  直接在这里按顺序 即可  -->
    <repositories>
        <!--aliyun -->
        <repository>
            <id>aliyun</id>
            <name>aliyun repository</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        </repository>
    </repositories>


    <dependencies>
        <!-- log4j2 日志-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
            <version>${spring.boot.version}</version>
        </dependency>
        <!-- es transport client  es 后续计划 放弃 此client  使用 http-->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>transport</artifactId>
            <version>${es.version}</version>
        </dependency>
        <!-- es  高级  http client -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>6.2.3</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>1.5.10.RELEASE</version>
        </dependency>
        <!--fastjson -->
        <!-- fast json -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.15</version>
        </dependency>

        <dependency>
            <groupId>net.inemar.utility</groupId>
            <artifactId>log4j2elastic</artifactId>
            <version>3.1.0</version>
        </dependency>

    </dependencies>
    <build>
        <plugins>
            <!-- 打包配置 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring.boot.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
2:编写多线程模式的elasticSearchClientUtils
使用多线程模式 做队列异步发送到es    
这里建议用同步方法实现 用中间件级别的mq去做队列  
```
package com.ming;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.util.NamedThreadFactory;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * es client   使用 高级 http 客户端
 * <p>
 * 为了避免 高并发导致  es 队列满了
 * 使用 请求合并策略:
 * 当线程池使用量在百分50内 不合并请求
 * 当线程池使用量超过百分50  合并请求 进行批量操作
 *
 * @author ming
 * @date 2018-03-30 10:51
 */
public class ElasticSearchClientUtils {
    /**
     * 重入锁 保证 进行 转化批量插入的时候 result 是原子操作
     */
    static final transient ReentrantLock lock = new ReentrantLock();
    private static final Logger l = LogManager.getLogger(ElasticSearchClientUtils.class);
    static volatile int isRuning = 0;
    static List<InsertDao> result = new CopyOnWriteArrayList<>();
    private static RestHighLevelClient restHighLevelClient;

    // 初始化
    static {
        init();
    }

    /**
     * 初始化 es client
     *
     * @author ming
     * @date 2018-03-30 10:55
     */
    public static void init() {
        //获取 构造器
        RestClientBuilder builder = RestClient.builder(new HttpHost(ElasticSearchConfig.host, ElasticSearchConfig.port, ElasticSearchConfig.type));
        //设置最大重试时间
        builder.setMaxRetryTimeoutMillis(ElasticSearchConfig.maxRetryTimeoutMillis);
        // 设置 请求回调配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(ElasticSearchConfig.requestConfig_ConnectTimeout);
            requestConfigBuilder.setSocketTimeout(ElasticSearchConfig.requestConfig_SocketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(ElasticSearchConfig.requestConfig_ConnectionRequestTimeout);
            return requestConfigBuilder;
        });
        //设置 http client 配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnPerRoute(ElasticSearchConfig.httpClientConfig_maxConnPerRoute);
            httpClientBuilder.setMaxConnTotal(ElasticSearchConfig.httpClientConfig_maxConnTotal);
            return httpClientBuilder;
        });
        //创建 client
        restHighLevelClient = new RestHighLevelClient(builder);
        //初始化 index

        try {
            createIndex(ElasticSearchConfig.index, ElasticSearchConfig.index);
        } catch (ElasticsearchStatusException e) {
            //创建索引重复 报400 错误  当不是400错误 继续抛出异常
            if (e.status() != RestStatus.BAD_REQUEST) {
                throw e;
            }
        }


    }

    /**
     * 创建 index
     *
     * @author ming
     * @date 2018-03-30 10:56
     */
    public static void createIndex(String type, String index) {
        String mappingSource = "  {\n" +
                "    \"" + index + "\": {\n" +
                "      \"properties\": {\n" +
                "        \"message\": {\n" +
                "          \"type\": \"text\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        createIndexRequest.mapping(type, mappingSource, XContentType.JSON);
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest);

            System.out.println(JSON.toJSONString(createIndexResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询是否存在 index
     *
     * @author ming
     * @date 2018-04-01 10:07
     */
    public static boolean indexIsExists(String index) throws IOException {
        return restHighLevelClient.exists(new GetRequest(index));
    }

    /**
     * 插入数据到 index中
     *
     * @param index
     * @param type
     * @param source
     * @return int
     * @throws IOException
     * @author ming
     * @date 2018-03-30 11:09
     */
    public static void insertToIndex(String index, String type, Object source) {
        IndexRequest indexRequest = new IndexRequest(index, type);
        indexRequest.source(JSON.toJSONString(source), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bulkInsert(List<InsertDao> insertDaoList) {
        if (insertDaoList.isEmpty()) {
            l.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            throw new RuntimeException("xxxxxx" + JSON.toJSONString(insertDaoList));
        }
        BulkRequest bulkRequest = new BulkRequest();
        insertDaoList.forEach(r -> {
            IndexRequest indexRequest = new IndexRequest(r.index, r.type);
            indexRequest.source(JSON.toJSONString(r.source), XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        try {

            restHighLevelClient.bulk(bulkRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用自定义线程池  类似cached executor service的线程池
     *
     * @author ming
     * @date 2018-03-31 13:04
     */
    public static void insertToIndexAsync(String index, String type, Object source) {
        ThreadPool.executeTask(() -> insertToIndex(index, type, source));
    }

    /**
     * 请求合并
     * todo  待解决 存在 日志丢失
     *
     * @author ming
     * @date 2018-04-01 10:55
     */
    public static synchronized void mergeInsertToIndexAsync(String index, String type, Object source) {
        if (ThreadPool.isMerge()) {
            //todo ming 暂定30  再多容易gg  理论上 在一定限度内 加大此条件 会增加效率 但是会增加丢失日志的风险
            if (isRuning >= 30) {
                System.out.println("result::" + JSON.toJSONString(result));
                ThreadPool.executeTask(() -> bulkInsert(new ArrayList<>(result)));
                isRuning = 0;
                result.clear();
            } else {
                result.add(new InsertDao(index, type, source));
                isRuning++;
            }
        } else {
            insertToIndexAsync(index, type, source);
        }
    }

    /**
     * es 配置
     *
     * @author ming
     * @date 2018-04-01 10:17
     */
    static class ElasticSearchConfig {
        public static final String host = "localhost";
        public static final int port = 9200;
        public static final String type = "http";
        public static final String index = "ming-index-";
        public static final int maxRetryTimeoutMillis = 5 * 60 * 1000;
        public static final int requestConfig_ConnectTimeout = 3000;
        public static final int requestConfig_SocketTimeout = 3000;
        public static final int requestConfig_ConnectionRequestTimeout = 3000;
        public static final int httpClientConfig_maxConnPerRoute = 1000;
        public static final int httpClientConfig_maxConnTotal = 1000;

    }

    static class InsertDao {
        private String index;
        private String type;
        private Object source;


        public InsertDao(String index, String type, Object source) {
            this.index = index;
            this.type = type;
            this.source = source;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }
    }

    /**
     * es client 线程池配置
     *
     * @author ming
     * @date 2018-03-31 12:59
     */
    static class ThreadPool {

        /**
         * todo ming es 默认执行线程池 是12线程  过多 会导致 gg   建议做请求合并处理
         */
        private static int coreSize = 5;
        private static int maxSize = 10;
        private static long keepAliveTime = 60 * 60 * 3L;
        private static TimeUnit timeUnit = TimeUnit.SECONDS;
        private static BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(10000);
        private static ThreadFactory factory = new NamedThreadFactory("es-client-");
        private static RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
            // todo ming  线程池异常处理
            try {
                executor.getQueue().put(r);
            } catch (final InterruptedException e) {
                throw new RuntimeException("重新添加 到队列失败");
            }
        };


        /**
         * 定制的线程池
         *
         * @author ming
         * @date 2018-03-30 13:40
         */
        private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, timeUnit, queue, factory, rejectedExecutionHandler);

        /**
         * 执行任务
         *
         * @author ming
         * @date 2018-03-30 13:48
         */
        public static void executeTask(Runnable runnable) {
            if (!executorService.isShutdown()) {
                executorService.execute(runnable);
            }
        }


        /**
         * 判断是否 合并   当woker数量超过 最大线程数的一半 返回true 进行请求合并
         *
         * @author ming
         * @date 2018-04-01 11:17
         */
        public static boolean isMerge() {
            int poolSize = ThreadPool.executorService.getPoolSize();
            int maxSize = ThreadPool.executorService.getMaximumPoolSize();
            return (maxSize / 2) < poolSize;
        }

        public static ThreadPoolExecutor getExecutorService() {
            return executorService;
        }
    }
}
```
3:编写elasticSearchAppender
借助elasticSearchUtils 方法 实现 esAppender
```
package com.ming;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.message.MapMessage;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * log4j2 es 适配器 基于 es client
 * 不使用 transport client  而是使用 http client  这个是官方的支持的  es 将在7.0之后 抛弃 transport client
 *
 * @author ming
 * @link https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.2/java-rest-high.html
 * <p>
 * 暂时不使用 queue  直接 线程池执行
 * @date 2018-03-28 11:39
 */
@Plugin(name = "elasticSearchClientAppender", category = "core", elementType = "appender", printObject = true)
public class ElasticSearchClientAppender extends AbstractAppender {
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat formatT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private String elasticUri;
    private String elasticCluster;
    private String indexName;
    private String indexRotate;


    public ElasticSearchClientAppender(String name,
                                       Filter filter,
                                       Layout<? extends Serializable> layout,
                                       boolean ignoreExceptions,
                                       String indexName) {
        super(name, filter, layout, ignoreExceptions);
        this.indexName = indexName;
    }

    /**
     * 插件工厂  生产 elasticSearchAppender
     * 接受 xml中的配置
     *
     * @author ming
     * @date 2018-03-29 11:18
     */
    @PluginFactory
    public static ElasticSearchClientAppender createAppender(@PluginAttribute("name") String name,
                                                             @PluginElement("Filter") Filter filter,
                                                             @PluginElement("Layout") Layout<? extends Serializable> layout,
                                                             @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
                                                             @PluginAttribute(value = "index", defaultString = "ming") String indexName,
                                                             @PluginAttribute(value = "uri") String elastic_local,
                                                             @PluginAttribute(value = "cluster") String elastic_cluster,
                                                             @PluginAttribute(value = "indexRotate", defaultString = "DAY") String indexRotate,
                                                             @PluginAttribute(value = "type", defaultString = "logs") String type,
                                                             @PluginAttribute(value = "node") String node,
                                                             @PluginAttribute(value = "service") String service,
                                                             @PluginAttribute(value = "bufferSize", defaultInt = 5000) Integer bufferSize,
                                                             @PluginAttribute(value = "expiryTime", defaultLong = -1) Long expiry,
                                                             @PluginAttribute(value = "expiryUnit", defaultString = "d") String expiryUnit,
                                                             @PluginAttribute("level") Level level,
                                                             @PluginAttribute("levelin") Level levelin) {


        return new ElasticSearchClientAppender(name, filter, layout, ignoreExceptions, indexName);
    }

    ;


    /**
     * 具体的 输出日志的方法
     *
     * @author ming
     * @date 2018-03-29 11:24
     */
    @Override
    public void append(LogEvent event) {

        try {
            Map<String, Object> logResultMap = new HashMap<>();

            //设置 log的 message
            if (event.getMessage() instanceof MapMessage) {
                logResultMap.putAll(((MapMessage) event.getMessage()).getData());
            } else {
                logResultMap.put("message", event.getMessage().getFormattedMessage());
            }

            logResultMap.put("@source", "mingjava");
            logResultMap.put("host", "minghost");
            logResultMap.put("@version", 1);
            logResultMap.put("@timestamp", formatT.format(new Date(event.getTimeMillis())));
            logResultMap.put("level", event.getLevel().toString());
            logResultMap.put("logger", event.getLoggerName());
            logResultMap.put("loggerFQDN", event.getLoggerFqcn());

            if (event.getMarker() != null) {
                logResultMap.put("marker", event.getMarker().toString());
            }
            logResultMap.put("context", event.getContextMap());
            //替换 本身的对象解析  使用fast生成 string  直接 source 成 byte
            ElasticSearchClientUtils.insertToIndexAsync(ElasticSearchClientUtils.ElasticSearchConfig.index,ElasticSearchClientUtils.ElasticSearchConfig.index,logResultMap);
            //ElasticSearchClientUtils.mergeInsertToIndexAsync(ElasticSearchClientUtils.ElasticSearchConfig.index, ElasticSearchClientUtils.ElasticSearchConfig.index, logResultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

```
4:配置log4j2.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration status="INFO" monitorInterval="30">
    <appenders>
        <!--这个输出控制台的配置-->
        <console name="Console" target="SYSTEM_OUT">
            <!--输出日志的格式-->
            <PatternLayout pattern="%highlight{[ %p ] [%-d{yyyy-MM-dd HH:mm:ss}] [%l] %m%n}"/>
        </console>

        <!-- 这个就是自定义的Appender -->
        <ElasticSearchClientAppender name="elasticSearchClientAppender">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] [%-5p] {%F:%L} - %m%n"/>
        </ElasticSearchClientAppender>
    </appenders>

    <loggers>
        <root level="info">
            <appender-ref ref="Console"/>
            <appender-ref ref="elasticSearchClientAppender"/>
        </root>
    </loggers>
</configuration>
```
5:编写测试类
```
package com.ming;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Test {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(Test.class);

    /**
     * 测试输出日志
     * ALL
     * TRACE
     * DEBUG
     * INFO
     * WARN
     * ERROR
     * FATAL
     * OFF
     *
     * @author ming
     * @date 2018-03-29 13:02
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            logger.log(Level.ALL, "ming all");
            logger.trace("ming tarce" + i);
            logger.debug("ming debug" + i);
            logger.info("ming  info " + i);
            logger.warn("ming warn " + i);
            logger.error("ming error" + i);
            logger.fatal("ming fatal" + i);
            logger.log(Level.OFF, "ming off" + i);
        }
        System.out.println("---------");
        System.out.println("耗时:" + (System.currentTimeMillis() - begin));
    }


}
```
6：查看es上日志
等到 控制台不输出日志 
登陆kibana http://localhost:5601
配置 index 即可  查看到刚刚产出的日志  

#### 总结
实例没有接入 中间件级别的mq  而是采用jdk中的executorService 来实现多线程+队列 去发送日志到es
生产环境 最好使用中间件级别的mq  因为mq会保证消息的最终执行结果  基本不会丢失日志  

