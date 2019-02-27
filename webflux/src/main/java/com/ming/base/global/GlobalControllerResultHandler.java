package com.ming.base.global;

import com.ming.base.global.result.ResponseResultBody;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.HandlerResultHandler;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.AbstractMessageWriterResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局控制器增强
 *
 * @author ming
 * @date 2019-02-26 17:32:02
 */
public class GlobalControllerResultHandler extends AbstractMessageWriterResultHandler implements HandlerResultHandler{

    /**
     * Basic constructor with a default {@link ReactiveAdapterRegistry}.
     * @param writers writers for serializing to the response body
     * @param resolver to determine the requested content type
     */
    public GlobalControllerResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        this(writers, resolver, ReactiveAdapterRegistry.getSharedInstance());
    }

    /**
     * Constructor with an {@link ReactiveAdapterRegistry} instance.
     * @param writers writers for serializing to the response body
     * @param resolver to determine the requested content type
     * @param registry for adaptation to reactive types
     */
    public GlobalControllerResultHandler(List<HttpMessageWriter<?>> writers,
                                         RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {

        super(writers, resolver, registry);
        setOrder(100);
    }


    @Override
    public boolean supports(HandlerResult result) {
        MethodParameter returnType = result.getReturnTypeSource();
        Class<?> containingClass = returnType.getContainingClass();
        return (AnnotatedElementUtils.hasAnnotation(containingClass, ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class));
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        ResponseResultBody<?> responseResultBody = new ResponseResultBody<>(result.getReturnValue());
        MethodParameter bodyTypeParameter = result.getReturnTypeSource();
        return writeBody(responseResultBody, bodyTypeParameter, exchange);
    }
}
