package com.ming.base;


import com.ming.base.global.GlobalControllerResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

@Configuration
public class WebConfig extends WebFluxConfigurationSupport {
    @Bean
    public GlobalControllerResultHandler responseBodyResultHandler(ServerCodecConfigurer serverCodecConfigurer, RequestedContentTypeResolver requestedContentTypeResolver, ReactiveAdapterRegistry reactiveAdapterRegistry) {
        return new GlobalControllerResultHandler(serverCodecConfigurer.getWriters(),
                requestedContentTypeResolver, reactiveAdapterRegistry);
    }


}
