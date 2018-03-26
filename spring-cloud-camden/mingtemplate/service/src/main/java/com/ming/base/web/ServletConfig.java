package com.ming.base.web;

import com.ming.base.web.filter.WrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * servlet 相关配置
 *
 * @author ming
 * @date 2018-01-24 17:50
 */
@Configuration
public class ServletConfig {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("urlFilter");
        registration.setOrder(1);
        return registration;
    }


}
