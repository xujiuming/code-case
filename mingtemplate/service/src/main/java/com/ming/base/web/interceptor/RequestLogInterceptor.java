package com.ming.base.web.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**访问日志 拦截器
 *打印debug 级别 访问日志  通过调整 日志级别忽略此拦截器
 *
 * @author ming
 * @date 2018-01-25 10:52
 */
@Log4j2
public class RequestLogInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("[request log]");
        sb.append("[params::"+ JSON.toJSONString(request.getParameterMap())+"]");
        sb.append("[cookies::"+ JSON.toJSONString(request.getCookies())+"]");
        log.debug(sb);
        return super.preHandle(request, response, handler);
    }
}
