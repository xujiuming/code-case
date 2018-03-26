package com.ming.base.web;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author com.ming
 * @date 2017-11-10 13:58
 */
@ControllerAdvice
@Log4j2
public class BaseGlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public com.ming.base.web.ReturnJsonBody defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        com.ming.base.web.ReturnJsonBody rb = new com.ming.base.web.ReturnJsonBody();
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            //rb.setCode(se.getCode());
            //rb.setMsg(se.getMsg());
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
