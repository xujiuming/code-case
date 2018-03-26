package com.ming.base.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * web相关配置
 *
 * @author ming
 * @date 2017-09-02
 */
@Configuration
public class WebConfig {

    /**
     * 错误页面处理
     *
     * @author ming
     * @date 2017-09-02
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedValueResolverAware() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        };
    }
}
