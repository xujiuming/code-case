package com.ming.base.mvc;

import com.ming.base.mvc.exception.ExceptionEnum;
import com.ming.base.mvc.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常拦截 处理  返回标准的 response
 *
 * @author ming
 * @date 2018-06-18 18:52:53
 */
@ControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ReturnJsonBody<?>> defaultExceptionHandler(Exception e) {
        ReturnJsonBody<?> returnJsonBody = new ReturnJsonBody<>();
        if (e instanceof ServiceException) {
            ExceptionEnum exceptionEnum = ((ServiceException) e).getExceptionEnum();
            returnJsonBody.setCode(exceptionEnum.getCode());
            returnJsonBody.setMsg(exceptionEnum.getMsg());
            returnJsonBody.setError(e.getMessage());
            return new ResponseEntity<>(returnJsonBody, exceptionEnum.getHttpStatus());
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
