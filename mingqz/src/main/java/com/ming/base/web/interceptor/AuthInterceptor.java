package com.ming.base.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证用户及其权限 拦截器
 *
 * @author ming
 * @date 2018-01-17 14:55
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {


    /**默认    不通过
     *
     *
     * @author ming
     * @date 2018-01-18 15:14
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        if (handler instanceof HandlerMethod) {
            flag = validataUser(request, response, (HandlerMethod) handler);
            flag = valiResource();
        }
        if (!flag){
            response.sendError(4003);
        }
        return flag;
    }


    /**
     * 验证用户  使用token 登陆
     *
     * @param request
     * @param response
     * @param handler
     * @author ming
     * @date 2018-01-18 15:06
     */
    private boolean validataUser(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {

        return true;
    }

    private boolean valiResource() {
        return true;
    }
}
