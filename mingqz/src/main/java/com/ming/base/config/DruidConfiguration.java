package com.ming.base.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import com.ming.core.utils.ExecuteStatUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;


/**
 * druid 线程池设定
 *
 * @author ming
 * @date 2017-12-13 19:01
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfiguration {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;
    private String validationQuery;

    private boolean testWhileIdle;
    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        ExecuteStatUtils.excuteTimeStat(() -> {
            servletRegistrationBean.setServlet(new StatViewServlet());
            servletRegistrationBean.addUrlMappings("/druid/*");
            Map<String, String> initParameters = Maps.newHashMap();
            // 用户名
            initParameters.put("loginUsername", "ming");
            // 密码
            initParameters.put("loginPassword", "ming");
            // 禁用HTML页面上的“Reset All”功能
            initParameters.put("resetEnable", "false");
            // IP白名单 (没有配置或者为空，则允许所有访问)
            initParameters.put("allow", "");
            // IP黑名单 (存在共同时，deny优先于allow)
            initParameters.put("deny", "192.168.20.38");
            servletRegistrationBean.setInitParameters(initParameters);
        }, "初始化 druid 数据库连接池 管理页面");

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setFilters(filters);
        return datasource;
    }

}

