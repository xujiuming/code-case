package com.ming.base.scheduler;

import org.springframework.context.annotation.Configuration;

/**
 * @author ming
 * @date 2017-11-08 14:36
 */
@Configuration
public class SchedulerConfig {

   /* @Bean(name = "mingSchedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setQuartzProperties(quartzProperties());
        return factoryBean;
    }
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/config/quartz.properties"));
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }*/
}
