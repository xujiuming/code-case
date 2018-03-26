package com.ming.base.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.ming.base.config.ThreadPoolConfig;
import com.ming.common.entity.log.AccessLog;
import com.ming.common.service.api.log.AccessLogService;
import com.ming.core.utils.HttpUtils;
import com.ming.core.utils.SpringBeanManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Executor;

/**
 * 记录登录日志
 *
 * @author ming
 * @date 2017-11-06 14:13
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //异步 记录登陆日志  尽量减少对业务的访问速度影响
        SpringBeanManager.getBean(ThreadPoolConfig.SIMPLE_EXECUTOR_POOL, Executor.class).execute(() -> {
            AccessLog accessLog = new AccessLog();
            accessLog.setUri(request.getRequestURI());
            accessLog.setQueryString(JSON.toJSONString(request.getParameterMap()));
            accessLog.setIp(HttpUtils.getIP(request));
            accessLog.setUa(HttpUtils.getUA(request));
            accessLog.setCreateTimeMillis(System.currentTimeMillis());
            //todo  下列信息暂时没有
            accessLog.setUid(0L);
            accessLog.setUserType(0);
            accessLog.setToken("token");
            SpringBeanManager.getBean(AccessLogService.class).save(accessLog);
        });
        return super.preHandle(request, response, handler);
    }
}
