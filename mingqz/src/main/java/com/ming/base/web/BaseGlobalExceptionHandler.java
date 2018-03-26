package com.ming.base.web;

import com.ming.base.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author ming
 * @date 2017-11-10 13:58
 */
@ControllerAdvice
@Slf4j
public class BaseGlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnJsonBody defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ReturnJsonBody rb = new ReturnJsonBody();
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            rb.setCode(se.getCode());
            rb.setMsg(se.getMsg());
        } else {
            rb.setCode(1);
            rb.setMsg("系统异常");
            e.printStackTrace();
            rb.setStack(ExceptionUtils.getStackTrace(e));
            /*try {
                addServerExceptionLog(req, e);
            } catch (Exception ex) {
                LOGGER.error("add server exception error {}", ExceptionUtils.getStackTrace(ex));
            }
*/
        }
        return rb;
    }

    // public abstract void addServerExceptionLog(HttpServletRequest request, Throwable throwable);

}
