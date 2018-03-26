package com.ming.base.web.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域拦截器
 * 思考了半天 最终决定 还是用拦截器作cors 跨域设定
 * 不采用 Corsegistry方式
 *
 * @author com.ming
 * @date 2017-12-18 10:45
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {


    /**
     * @author com.ming
     * @date 2017-12-18 10:46
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是预检请求 直接  不进行后续 拦截器校验 直接返回
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String origin = "*";
        if (!"*".equalsIgnoreCase(request.getHeader("origin"))) {
            origin = request.getHeader("origin");
        }
        // 跨域参数设置
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        // todo  ming  跨域 允许headers 列表
        response.setHeader("Access-Control-Allow-Headers", "");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "0");
        return true;
    }
}
