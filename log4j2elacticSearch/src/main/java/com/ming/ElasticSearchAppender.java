package com.ming;

import com.alibaba.fastjson.JSON;
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
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * log4j2  和es交互的适配器
 * 不采用 socket appender 原因是 socket appender 功能很齐全 但是 比较麻烦 改起来
 * 不如直接用es client 实现一个简单的appender
 * ElasticAppender的实现是通过设定一个发送线程去轮询 queue 发送到es  这样 单机 环境下 很合适  如果是分布式环境 不如直接丢 mq 中好用
 * 我的思路 初期 appender 只要简单的发送日志到 es上 至于性能问题  需要的时候 通过桥接第三方 mq 去实现 本身不做复杂的功能
 * 每次 直接从线程池调用一个线程出来 执行此次task  不做 内部queue
 *
 * @author ming
 * @date 2018-03-28 11:39
 */
@Plugin(name = "EsAppender", category = "core", elementType = "appender", printObject = true)
public class ElasticSearchAppender extends AbstractAppender {
    private static final long serialVersionUID = 1L;
    private static  final  SimpleDateFormat formatT =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private String elasticUri;
    private String elasticCluster;
    private String indexName;
    private String indexRotate;


    public ElasticSearchAppender(String name,
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
    public static ElasticSearchAppender createAppender(@PluginAttribute("name") String name,
                                                       @PluginAttribute(value = "uri") String elastic_local,
                                                       @PluginAttribute(value = "cluster") String elastic_cluster,
                                                       @PluginAttribute(value = "index", defaultString = "logstash") String index,
                                                       @PluginAttribute(value = "indexRotate", defaultString = "DAY") String indexRotate,
                                                       @PluginAttribute(value = "type", defaultString = "logs") String type,
                                                       @PluginAttribute(value = "node") String node,
                                                       @PluginAttribute(value = "service") String service,
                                                       @PluginAttribute(value = "bufferSize", defaultInt = 5000) Integer bufferSize,
                                                       @PluginAttribute(value = "expiryTime", defaultLong = -1) Long expiry,
                                                       @PluginAttribute(value = "expiryUnit", defaultString = "d") String expiryUnit,
                                                       @PluginAttribute("level") Level level,
                                                       @PluginAttribute("levelin") Level levelin,
                                                       @PluginElement("Filters") Filter filter) {


        return new ElasticSearchAppender(name, filter, layout, config, indexName);
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
        BulkRequestBuilder bulkRequestBuilder = getEsElient().prepareBulk();
        String resolveIndex = "ming";
        Map<String,Object> logResultMap = new HashMap<>();

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
        IndexRequestBuilder d = getEsElient().prepareIndex(resolveIndex, "mingtype")
                .setSource(JSON.toJSONString(logResultMap),XContentType.JSON);
        bulkRequestBuilder.add(d);
        //发送
        bulkRequestBuilder.execute();
    }


    /**
     * 获取 es client
     *
     * @author ming
     * @date 2018-03-29 17:04
     */
    private TransportClient getEsElient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "ming")
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }


}
