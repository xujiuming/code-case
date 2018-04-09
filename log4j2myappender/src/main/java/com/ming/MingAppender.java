package com.ming;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;


/**
 * log4j2  自定义适配器
 *
 * @author ming
 * @date 2018-03-28 11:39
 */
@Plugin(name = "MingAppender", category = "core", elementType = "appender", printObject = true)
public class MingAppender extends AbstractAppender {
    private static final long serialVersionUID = 1L;


    public MingAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }


    /**
     * 插件工厂  生产 appender
     * 参数是 xml中的配置
     *
     * @author ming
     * @date 2018-03-29 11:18
     */
    @PluginFactory
    public static MingAppender createAppender(@PluginAttribute("name") String name,
                                              @PluginElement("Filter") final Filter filter,
                                              @PluginElement("Layout") Layout<? extends Serializable> layout,
                                              @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {


        return new MingAppender(name, filter, layout, ignoreExceptions);
    }


    /**
     * 具体的 输出日志的实现方法
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
