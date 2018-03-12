package com.ming.base.web;

import com.ming.base.web.interceptor.RequestLogInterceptor;
import com.ming.base.web.interceptor.CorsInterceptor;
import com.ming.base.web.interceptor.ResponseLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 拦截器配置
 *
 * @author com.ming
 * @date 2017-08-28 11点
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    /**
     * 添加拦截器
     *
     * @author com.ming
     * @date 2017-11-07 10:08
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //记录请求信息的日志  必须第一个
        registry.addInterceptor(new RequestLogInterceptor());
        // 跨域拦截器 必须第一个 避免 预检请求 经过后续拦截器
        registry.addInterceptor(new CorsInterceptor());

        //记录响应信息的日志 必须是最后一个
        registry.addInterceptor(new ResponseLogInterceptor());
        super.addInterceptors(registry);
    }
    /**
     * 特殊处理资源  方便 改变路径
     *
     * @author com.ming
     * @date 2017-11-14 14:28
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("classpath*:/static/")
                .addResourceLocations("classpath*:/templates/");
        super.addResourceHandlers(registry);
    }


    /**
     * 使用自定义消息转换器
     *
     * @author com.ming
     * @date 2018-01-18 09:59
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new DateMessageConverter());
        super.configureMessageConverters(converters);
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
        configurer.setUseSuffixPatternMatch(true);
        super.configurePathMatch(configurer);
    }


}
