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
            //ElasticSearchClientUtils.insertToIndexAsync(ElasticSearchClientUtils.ElasticSearchConfig.index,ElasticSearchClientUtils.ElasticSearchConfig.index,logResultMap);
            ElasticSearchClientUtils.mergeInsertToIndexAsync(ElasticSearchClientUtils.ElasticSearchConfig.index, ElasticSearchClientUtils.ElasticSearchConfig.index, logResultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
