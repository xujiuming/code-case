package com.ming.base.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应日志
 * debug级别
 *
 * @author com.ming
 * @date 2017-11-06 18:15
 */
@Log4j2
public class ResponseLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StringBuffer sb= new StringBuffer();
        sb.append("[response log]");
        sb.append("[status::"+response.getStatus()+"]");
        log.debug(sb);
    }
}
