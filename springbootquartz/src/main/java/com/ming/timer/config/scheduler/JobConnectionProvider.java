package com.ming.timer.config.scheduler;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import com.ming.timer.utils.SpringBeanManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.utils.ConnectionProvider;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 处理 job的数据源   采用 spring 中注册的 data source
 *
 * @author ming
 * @date 2018-05-14 09:55
 */
@Component
@DependsOn("springBeanManager")
public class JobConnectionProvider implements ConnectionProvider {

    private static final Logger logger = LogManager.getLogger(JobConnectionProvider.class);


    private DruidDataSource dataSource ;
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

    @Override
    public void shutdown() throws SQLException {
        logger.info("shutdown  data source  connection ");
    }

    @Override
    public void initialize() throws SQLException {
        Environment environment = SpringBeanManager.getBean(Environment.class);
        Preconditions.checkNotNull(environment, "初始化quartz数据源 无法获取参数");
        String prefix = "spring.datasource.";
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + "driver-class-name"));
        dataSource.setUrl(environment.getProperty(prefix + "url"));
        dataSource.setUsername(environment.getProperty(prefix + "username"));
        dataSource.setPassword(environment.getProperty(prefix + "password"));
        logger.info("init data source  connection ");
    }
}
