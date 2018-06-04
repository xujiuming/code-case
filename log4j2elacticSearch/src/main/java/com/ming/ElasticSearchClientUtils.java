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
