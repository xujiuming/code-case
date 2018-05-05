package com.ming.timer.config.scheduler;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ming
 * @date 2017-11-08 14:36
 */
@Configuration
@DependsOn("springBeanManager")
public class SchedulerConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //根据 配置环境 使用不同环境的 quartz配置
        //String env = SpringBeanManager.getBean(Environment.class).getProperty("spring.cloud.config.profile");
        //if (StringUtils.isEmpty(env)) {
        //    throw new NullPointerException("schedulerConfig:: spring.cloud.config.profile is null");
        //}
        //propertiesFactoryBean.setLocation(new ClassPathResource("/quartz-" + env + ".properties"));
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     *
     * @author ming
     * @date 2018-04-26 14:20
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     *
     * @author ming
     * @date 2018-04-26 14:20
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException {
        return schedulerFactoryBean.getScheduler();
    }
}
