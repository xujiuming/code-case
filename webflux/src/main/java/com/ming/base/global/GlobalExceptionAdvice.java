package com.ming.base.global;

import com.ming.base.global.exception.CodeException;
import com.ming.base.global.result.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * 全局控制器 异常增强
 *
 * @author ming
 * @date 2019-02-26 17:33:17
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Mono<?> exceptionHandler(Exception e) {
        ResponseResultBody responseResultBody;
        if (e instanceof CodeException) {
            CodeException serviceException = (CodeException) e;
            CodeException.CodeEnum codeEnum = serviceException.getCodeEnum();
            responseResultBody = new ResponseResultBody(codeEnum.getCode(), codeEnum.getMsg(), serviceException.getExtraInfo());
        } else {
            responseResultBody = new ResponseResultBody(-1, "未知异常", e.getLocalizedMessage());
            log.error("未知异常:{},{}", e.getMessage(), e);
            e.printStackTrace();
        }
        return Mono.just(responseResultBody);
    }
}
