package com.ming.timer.base.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@Configuration
public class WebInterceptors extends WebMvcConfigurationSupport {
    public WebInterceptors() {
        super();
    }


    /**
     * 添加拦截器
     *
     * @author ming
     * @date 2017-11-07 10:08
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new ThymeleafLayoutInterceptor());
        registry.addInterceptor(new AccessLogInterceptor());
        registry.addInterceptor(new ResponseHandler());
        super.addInterceptors(registry);
    }

}
