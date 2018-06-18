package com.ming.base.mvc.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义服务异常
 *
 * @author ming
 * @date 2017-06-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    /**
     * 异常枚举
     */
    private ExceptionEnum exceptionEnum;
    /**
     * 额外的消息
     */
    private String error;

    public ServiceException(ExceptionEnum exceptionEnum, String error) {
        this.exceptionEnum = exceptionEnum;
        this.error = error;
    }
    public ServiceException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}
