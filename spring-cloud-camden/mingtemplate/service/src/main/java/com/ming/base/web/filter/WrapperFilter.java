package com.ming.base.web.filter;

import com.ming.base.web.wrapper.UrlWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 绑定 ServletHttpWrapper  filter
 *
 * @author ming
 * @date 2018-01-24 17:47
 */
public class WrapperFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 添加 urlWrapper 处理统一前缀的
        chain.doFilter(new UrlWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
