package com.ming.base.global.result;


import com.ming.base.global.exception.CodeException;
import lombok.Data;

/**
 * @author ming
 * @date 2018-09-04 14:50:12
 */
@Data
public class ResponseResultBody<T> {
    /**
     * 编码
     * {@linkplain CodeException.CodeEnum#code}
     */
    private Integer code;
    /**
     * 错误编码提示信息
     * <p>
     * {@linkplain CodeException.CodeEnum#msg}
     */
    private String msg;
    /**
     * 补充信息  测试开启 生产禁用此字段
     * {@linkplain CodeException.CodeEnum#extraInfo}
     */
    private String extraInfo;

    /**
     * 返回对象
     */
    private T data;

    /**
     * 异常的时候使用的构造器
     *
     * @author ming
     * @date 2018-09-04 14:51:48
     */
    public ResponseResultBody(Integer code, String msg, String extraInfo) {
        this.code = code;
        this.msg = msg;
        this.extraInfo = extraInfo;
    }

    /**
     * 异常的时候使用的构造器
     *
     * @author ming
     * @date 2018-09-04 14:51:48
     */
    public ResponseResultBody(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ResponseResultBody(T data) {
        this.code = 0;
        this.data = data;
    }


}
