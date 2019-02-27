package com.ming.base.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义服务异常
 *
 * @author ming
 * @date 2017-06-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CodeException extends RuntimeException {

    /**
     * 异常编码
     */
    private final CodeEnum codeEnum;

    private String extraInfo;

    public CodeException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public CodeException(CodeEnum codeEnum, String extraInfo) {
        this.codeEnum = codeEnum;
        this.extraInfo = extraInfo;
    }


    /**
     * 自定义异常 编码
     * 总得来说  非预期异常返回-1
     * 其他的异常 按照寓意 尽量和http状态 4xx  5xx 绑定
     * 4xx:客户端异常
     * 5xx； 服务端异常
     * 其他整数:常规业务提示错误
     *
     * @author ming
     * @date 2018-08-29 09:49:44
     */
    @Getter
    @AllArgsConstructor
    public enum CodeEnum {
        /**
         * 系统异常
         */
        SYSTEM_ERROR(-1, "系统调用异常", HttpStatus.INTERNAL_SERVER_ERROR),

        /**
         * 数据为null
         */
        DATA_NOT_FOUND(40000, "数据为空", HttpStatus.NOT_FOUND);



        private Integer code;
        private String msg;
        private HttpStatus httpStatus;
    }

}


