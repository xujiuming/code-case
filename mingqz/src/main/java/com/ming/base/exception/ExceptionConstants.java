package com.ming.base.exception;


import lombok.Getter;

/**
 * 错误码表
 *
 * @author ming
 * @date 2017-07-16
 */
@Getter
public enum ExceptionConstants {
    /**
     * 业务未知异常
     */
    ERROR(1, "业务异常");

    private Integer num;
    private String content;


    ExceptionConstants(Integer num, String content) {
        this.num = num;
        this.content = content;
    }
}
