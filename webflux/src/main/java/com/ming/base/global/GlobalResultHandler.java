package com.ming.base.global;

import com.ming.base.global.result.ResponseResultBody;
import com.ming.core.utils.JacksonSingleton;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局控制器增强
 *
 * @author ming
 * @date 2019-02-26 17:32:02
 */
public class GlobalResultHandler extends ResponseBodyResultHandler {


    public GlobalResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }


    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        ResponseResultBody<?> responseResultBody = new ResponseResultBody<>(result.getReturnValue());
        MethodParameter bodyTypeParameter = null;
        try {
            //强制设置 bodyTypeParameter为 String
            bodyTypeParameter = new MethodParameter(String.class.getDeclaredMethod("toString"), -1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return writeBody(JacksonSingleton.writeString(responseResultBody), bodyTypeParameter, exchange);
    }
}
