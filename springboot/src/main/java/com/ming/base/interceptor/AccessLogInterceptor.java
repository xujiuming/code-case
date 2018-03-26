package com.ming.base.interceptor;

import com.alibaba.fastjson.JSON;
import com.ming.core.utils.HttpUtils;
import com.ming.core.utils.SpringBeanManagerUtils;
import com.ming.entity.log.AccessLog;
import com.ming.service.log.AccessLogService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录登录日志
 *
 * @author ming
 * @date 2017-11-06 14:13
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccessLog accessLog = new AccessLog();
        accessLog.setUri(request.getRequestURI());
        accessLog.setQueryString(JSON.toJSONString(request.getParameterMap()));
        accessLog.setIp(HttpUtils.getIP(request));
        accessLog.setUa(HttpUtils.getUA(request));
        accessLog.setCreateTimeMillis(System.currentTimeMillis());
        //todo  下列信息暂时没有
        accessLog.setUid(1L);
        accessLog.setUserType(1);
        accessLog.setToken("token");
        SpringBeanManagerUtils.getBeanByType(AccessLogService.class).save(accessLog);
        return super.preHandle(request, response, handler);
    }
}
