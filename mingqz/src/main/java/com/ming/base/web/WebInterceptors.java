package com.ming.base.web;

import com.ming.base.web.interceptor.AccessLogInterceptor;
import com.ming.base.web.interceptor.CorsInterceptor;
import com.ming.base.web.interceptor.ResponseHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 拦截器配置
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@Configuration
public class WebInterceptors extends WebMvcConfigurerAdapter {


    /**
     * 添加拦截器
     *
     * @author ming
     * @date 2017-11-07 10:08
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器 必须第一个 避免 预检请求 经过后续拦截器
        registry.addInterceptor(new CorsInterceptor());
        //注册 访问日志 拦截器
        registry.addInterceptor(new AccessLogInterceptor());
        //注册统一处理响应包头拦截器
        registry.addInterceptor(new ResponseHandlerInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 特殊处理资源  方便 改变路径
     *
     * @author ming
     * @date 2017-11-14 14:28
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("classpath*:/**")
                .addResourceLocations("classpath*:/static/")
                .addResourceLocations("classpath*:/templates/")
                .addResourceLocations("classpath*:/META-INF");
        super.addResourceHandlers(registry);
    }


    /**使用自定义消息转换器
     *
     *
     * @author ming
     * @date 2018-01-18 09:59
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(new DateMessageConverter());
        super.configureMessageConverters(converters);
    }


}
