package com.ming.base.web.wrapper;

import com.ming.base.constans.SystemConstans;
import com.ming.core.utils.EnvironmentUtils;
import com.ming.core.utils.SpringBeanManager;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;

/**
 * url统一增加前缀 处理   使用 httpservletrequestwrapper 来装饰
 * <p>
 * 注意: 实现wrapper 只是 包装了request的相应的方法 request本质是没有变化的
 *
 * @author ming
 * @date 2018-01-24 17:32
 */
public class UrlWrapper extends HttpServletRequestWrapper {


    /**
     * 当不符合前缀 跳转的默认页面
     */
    private static final String ERROR_DEFAULT_PATH = "/404";


    private HttpServletRequest request;


    public UrlWrapper(HttpServletRequest req) {
        super(req);
        request = req;
    }


    @Override
    public String getRequestURI() {
        return handleUriOrPath(request.getRequestURI(), getUriPrefix());
    }


    @Override
    public StringBuffer getRequestURL() {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme() + "://");
        sb.append(request.getServerName());
        sb.append(request.getServerPort());
        sb.append(handleUriOrPath(request.getRequestURI(), getUriPrefix()));
        return sb;
    }


    @Override
    public String getServletPath() {
        return handleUriOrPath(request.getServletPath(), getUriPrefix());
    }


    /**
     * 处理 uri或者path
     *
     * @param oldStr
     * @param startWith
     * @return String
     * @author ming
     * @date 2018-01-24 20:13
     */
    private String handleUriOrPath(String oldStr, String startWith) {
        String newServletPath = ERROR_DEFAULT_PATH;
        //排除 配置的uri
        for (String s : getExcludeUri()) {
            if (oldStr.startsWith(s)) {
                return oldStr;
            }
        }

        if (oldStr.startsWith(startWith)) {
            newServletPath = oldStr.substring(startWith.length());
        }
        return newServletPath;
    }


    /**
     * 获取 uri的前缀
     * 默认 /ming前缀
     *
     * @author ming
     * @date 2018-01-25 09:18
     */
    private String getUriPrefix() {
        return SpringBeanManager.getBean(Environment.class).getProperty(SystemConstans.CONTROLLER_PREFIX, "/ming");
    }


    /**
     * 排除url
     */
    /**
     * 获取排除的uri
     *
     * @author ming
     * @date 2018-01-25 10:36
     */
    @SuppressWarnings("unchecked")
    private List<String> getExcludeUri() {
        return EnvironmentUtils.getPropertyList(SpringBeanManager.getBean(Environment.class), SystemConstans.CONTROLLER_EXCLUE_URI);
    }

}
