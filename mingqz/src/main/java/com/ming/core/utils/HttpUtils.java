package com.ming.core.utils;

import com.ming.core.constans.SystemConstans;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 由于 个人更加喜欢使用 restTemplate 去发起请求 所以此工具类 不做发起请求功能
 * 只做常用对request response 操作工具类 不做发起请求工作
 *
 * @author ming
 * @date 2017-06-24
 */
public class HttpUtils {

    /**
     * 默认编码 utf 8
     *
     * @author ming
     * @date 2017-11-06 14:26
     */
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 避免字符魔法值
     *
     * @author ming
     * @date 2017-11-10 17:42
     */
    private static final String HTTPS = "https://";


    /**
     * 构建url
     *
     * @author ming
     * @date 2017-11-10 18:00
     */
    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), CHARSET_NAME));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    /**
     * 获取Cookie方法
     *
     * @param request request对象
     * @param name    Cookie名称
     * @return 值
     * @author ming
     * @date 2017-11-10 17:53
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     * @param maxAge   最长存活时间（秒）
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        addCookie(response, name, value, maxAge, "/");
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, "/");
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     * @param maxAge   最长存活时间（秒）
     * @param path     存放路径
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     * @param path     存放路径
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 移除Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, null, 0);
    }

    /**
     * @param request request对象
     * @return ip
     */
    public static String getIP(HttpServletRequest request) {
        // for UC browser
        String ip = request.getHeader("clientip");
        if (ip == null) {
            ip = request.getHeader("X-Real-IP");
            if (ip == null) {
                ip = request.getHeader("X-Forwarded-For");
                if (ip == null) {
                    ip = request.getRemoteAddr();
                }
            }
        }
        return ip;
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    public static String getUA(ServletRequest request) {
        return getUA((HttpServletRequest) request);
    }

    /**
     * 获取 防盗链信息
     *
     * @param request 请求
     * @return referer
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }


    /**
     * url参数编码，按照UTF-8来
     *
     * @param value 参数
     * @return 编码后的参数
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取参数
     *
     * @author ming
     * @date 2017-11-06 14:27
     */
    public static String getParam(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    /**
     * 获取数字类型参数
     *
     * @author ming
     * @date 2017-11-06 14:28
     */
    public static Integer getIntegerParam(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * 获取boolean  true/false  或者 0/1
     *
     * @author ming
     * @date 2017-11-06 14:37
     */
    public static Boolean getBooleanParam(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        //当值为 0或者1 也转换位boolean
        if (StringUtils.isNumeric(value)) {
            if (SystemConstans.ONE.equals(value)) {
                return Boolean.FALSE;
            } else if (SystemConstans.TWO.equals(value)) {
                return Boolean.TRUE;
            } else {
                throw new RuntimeException("数字只能传入1或者0可转换boolean");
            }
        }
        return Boolean.valueOf(value);
    }


}


