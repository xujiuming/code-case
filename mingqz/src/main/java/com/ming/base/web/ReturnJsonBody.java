package com.ming.base.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回json 字符串格式
 * json接口 返回的字符串格式
 *
 * @author ming
 * @date 2017-11-10 12:56
 */
@Data
@NoArgsConstructor
public class ReturnJsonBody implements Serializable {
    /**
     * 状态码
     */
    private long code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 附加信息
     */
    private String msg;
    /**
     * 异常信息
     */
    private String stack;

    /**
     * 返回成功的消息使用的构造函数
     *
     * @author ming
     * @date 2017-11-10 17:26
     */
    public ReturnJsonBody(long code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 返回异常的消息使用的构造函数
     *
     * @author ming
     * @date 2017-11-10 17:26
     */
    public ReturnJsonBody(long code, String msg, String stack) {
        this.code = code;
        this.msg = msg;
        this.stack = stack;
    }
}
