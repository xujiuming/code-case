package com.ming.base;


import com.ming.base.global.GlobalResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityResultHandler;

/**
 * 全局 web相关配置
 *
 * @author ming
 * @date 2019-03-06 13:49:25
 */
@Configuration
public class WebConfig extends WebFluxConfigurationSupport {
    @Autowired
    private ServerCodecConfigurer serverCodecConfigurer;

    @Autowired
    private RequestedContentTypeResolver requestedContentTypeResolver;

    /**
     * 配置 返回body 全局统一格式
     *
     * @author ming
     * @date 2019-03-06 13:49:43
     */
    @Override
    public ResponseBodyResultHandler responseBodyResultHandler() {
        return new GlobalResultHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }


}
