package com.ming;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
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
@Plugin(name = "esAppender",category = "core",elementType = "appender",printObject = true)
public class ElasticSearchAppender extends AbstractAppender {


    public ElasticSearchAppender() {
        super();
    }

    /*  接收配置文件中的参数 */
        @PluginFactory
        public static FileAppender createAppender(@PluginAttribute("name") String name) boolean ignoreExceptions) {
            return new ElasticSearchAppender(name, filter, layout, ignoreExceptions, fileName);
        }


}
