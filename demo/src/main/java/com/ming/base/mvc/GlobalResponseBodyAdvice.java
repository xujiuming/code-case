package com.ming.base.mvc;

import com.google.common.collect.ImmutableSet;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Set;

/**
 * 全局返回内容增强
 *
 * @author ming
 * @date 2018-06-18 19:07:51
 */
@ControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 需要处理的类型
     *
     * @author ming
     * @date 2017-11-10 14:05
     */
    private final Set<MediaType> jsonMediaType = new ImmutableSet.Builder<MediaType>()
            .add(MediaType.APPLICATION_JSON)
            .add(MediaType.APPLICATION_JSON_UTF8)
            .build();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(
            Object obj, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> converterType,
            ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //当类型 不属于 需要处理的包头的时候 直接返回obj
//        if (!jsonMediaType.contains(mediaType)) {
//            return obj;
//        }
        //当类型 是属于需要处理的时候 并且 obj不是ReturnJsonBody的时候 进行格式化处理
        if (!(obj instanceof ReturnJsonBody)) {
            obj = new ReturnJsonBody(0, obj);
        }
        return obj;
    }

}
