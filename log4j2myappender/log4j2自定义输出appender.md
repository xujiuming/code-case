#### log4j2
log4j2  是log4j 2.x版本 变化很多 
其实用法差不多 
默认提供一些 appender  但是可能不够用  还是要根据具体项目去是自定义appender 去做到日志的输出 
例如 输出到elk集群  可以利用socketAppender 去通过tcp协议发送日志到logstash格式化 然后归档到es  去做日志分析 
也可以自己通过实现 appender 输出到logstash 或者干脆输出到es中 都是可以的  
##### 自定义appender 
0:相关依赖
主要就是 log4j2一些依赖 这里偷懒 直接使用spring boot starter log4j2
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ming</groupId>
    <artifactId>log4j2-myappender</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>jar</packaging>

    <properties>
        <spring.boot.version>1.5.10.RELEASE</spring.boot.version>
        <es.version>6.2.3</es.version>
    </properties>


    <dependencies>
        <!-- log4j2 日志-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
            <version>${spring.boot.version}</version>
        </dependency>
        <!--fastjson -->
        <!-- fast json -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.15</version>
        </dependency>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.20</version>
            <scope>provided</scope>
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
1:一个appender类 
主要就是要继承 AbstractAppender(appender的模板)  和@Plugin(appender的基础信息描述) @PluginFactory(接受xml中的配置去生成appender)
```
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

```
2:配置log4j2.xml
配置自定义的appender  内部的属性和参数是@PluginFactory注解的方法的参数 
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
        <MingAppender name="mingAppender">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] [%-5p] {%F:%L} - %m%n"/>
        </MingAppender>

    </appenders>

    <loggers>
        <!-- 输出所有的日志 到控制台和 mingAppender-->
        <root level="all">
            <appender-ref ref="Console"/>
            <appender-ref ref="mingAppender"/>
        </root>
    </loggers>
</configuration>
```
3：使用log4j2 输出日志
log4j2 获取logger 方式和 之前从gerFactory中获取logger不一样 
是从LoggerManager中获取的 
或者直接使用lombok的@log4j2注解 
```

package com.ming;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 测试 log4j2 日志转换器
 *
 * @author ming
 * @date 2018-03-29 13:32
 */
@Log4j2
public class Test {
    private static final Logger loggerByLogManager = LogManager.getLogger(Test.class);

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
    public static void main(String[] args) {
        //logManager 提供的logging
        loggerByLogManager.log(Level.ALL, "ming all");
        loggerByLogManager.trace("ming tarce");
        loggerByLogManager.debug("ming debug");
        loggerByLogManager.info("ming  info ");
        loggerByLogManager.warn("ming warn ");
        loggerByLogManager.error("ming error");
        loggerByLogManager.fatal("ming fatal");
        loggerByLogManager.log(Level.OFF, "ming off");

        // lombok 的@log4j2 提供的logger
        log.log(Level.ALL, "ming all");
        log.trace("ming tarce");
        log.debug("ming debug");
        log.info("ming  info ");
        log.warn("ming warn ");
        log.error("ming error");
        log.fatal("ming fatal");
        log.log(Level.OFF, "ming off");
    }
}

```
#### 实际案例
公司的日志分析相关是我个人搭建的
主要是由es+kibana搭建成日志归档分析平台 
放弃 logstash 和filebeat之类的管道处理和采集器
直接使用 log4j2自定义appender 通过es的highLevelClient客户端 直接向es中发送数据 
中间加入mq  所有日志投递进mq  由专门的服务去消费传递到es中 
优点:
0:如果使用docker之类的容器 那么对环境的侵入性 几乎没有 直接启动就可以用  不用配置什么 配合上k8s这样神器  吊的一批
1:不生成文本文件 应用服务器不存在磁盘爆满的情况 
2:由于用mq异步掉了 可靠性、稳定性、伸缩性得到保证 不管多少日志 只要mq集群不挂 根本不抖
3:不需要使用额外的第三方采集 对服务器的编排或者容器的编排没有什么要求 反正不需要借助环境  
不需要去学习一些 归属于特定编排工具的日志插件 例如k8s的日志处理等 比较麻烦 伸缩性不太好保证
缺点:
0:对整体项目的日志需要调整  新项目好说、 祖传项目 基本gg
1：没有加logstash  无法做到多系统统一格式日志的整理  其实这里好说 就吧消费日志消息的appender 改改  向logstash 发送日志即可 
2:对网络的压力巨大  因为不管是调用基础服务 还是投递消息到mq 还是最终消费消息保存到es中 基本上都是网络io   
这个时候 就只能求运维换牛逼点的网卡、优化集群的网络相关处理了 
3:mq压力略高  最好把日志的mq集群与其他mq 独立开   不要和其他业务混合 否则会导致其他mq消息消费偏慢 
#### 总结
log4j2的appender 自定义起来很简单 实用性很高   
可以贴合各种项目的结构去做日志的相关处理  
代码实例地址:  https://github.com/xuxianyu/code-case/tree/master/log4j2myappender
