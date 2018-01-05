package com.ming.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * token 过滤器
 *
 * @author ming
 * @date 2017-10-19 14:38
 */
@Component
public class TokenFilter extends ZuulFilter {
    /**
     * 拦截器类型
     * pre：请求被路由之前调用
     * routing：路由请求时被调用
     * post:再routing 和error之后被调用
     * error:请求发生错误 调用
     *
     * @author ming
     * @date 2017-10-19 14:37
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 拦截器排序值 数值越小优先级越高
     *
     * @author ming
     * @date 2017-10-19 14:37
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这个过滤器是否需要执行
     *
     * @author ming
     * @date 2017-10-19 14:38
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (token == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(403);
            requestContext.setResponseBody("fuck！！！！！");
            return null;
        }
        return null;
    }
}
