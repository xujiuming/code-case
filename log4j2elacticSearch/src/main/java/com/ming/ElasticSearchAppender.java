package com.ming;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;


/**
 * log4j2  和es交互的适配器
 * 不采用 socket appender 原因是 socket appender 功能很齐全 但是 比较麻烦 改起来
 * 不如直接用es client 实现一个简单的appender
 *
 * @author ming
 * @date 2018-03-28 11:39
 */
@Plugin(name = "EsAppender", category = "core", elementType = "appender", printObject = true)
public class ElasticSearchAppender extends AbstractAppender {
    private static final long serialVersionUID = 1L;

    private String indexName;


    public ElasticSearchAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions,String indexName) {
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
                                                       @PluginElement("Filter") final Filter filter,
                                                       @PluginElement("Layout") Layout<? extends Serializable> layout,
                                                       @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
                                                       @PluginAttribute(value = "indexName",defaultString = "ming") String indexName) {


        return new ElasticSearchAppender(name,filter,layout,ignoreExceptions,indexName);
    }


    /**具体的 输出日志的方法
     *
     *
     * @author ming
     * @date 2018-03-29 11:24
     */
    @Override
    public void append(LogEvent event) {
        System.out.println(JSON.toJSONString(event));
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
    }
}
