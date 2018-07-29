package com.ming.base.mvc;


import lombok.Data;

/**
 * 返回json 格式
 *
 * @author ming
 * @date 2018-06-18 18:12:09
 */
@Data
public class ReturnJsonBody<T> {

    private Integer code;
    private String msg;
    private String error;
    private T data;


    public ReturnJsonBody() {
    }

    public ReturnJsonBody(Integer code, T data) {
        this.code = code;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
