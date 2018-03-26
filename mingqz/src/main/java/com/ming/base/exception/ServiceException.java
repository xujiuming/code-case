package com.ming.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 自定义服务异常
 *
 * @author ming
 * @date 2017-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    /**
     * 异常编码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 扩展参数
     */
    private Map<String, Object> extParams;

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
