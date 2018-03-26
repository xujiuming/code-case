package com.ming.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpUtils请从
 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
 * 相应的依赖请参照
 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
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
    private static final String charsetName = "UTF-8";

    /**
     * get
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */

    public static HttpResponse doGet(String host, String path,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }


    /**
     * post form
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Put String
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    /**
     * Put stream
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Delete
     *
     * @param host
     * @param path
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String host, String path,
                                        Map<String, String> headers,
                                        Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }

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
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        if (host.startsWith("https://")) {
            //  sslClient(httpClient);
            throw new RuntimeException("现阶段不支持 https");
        }

        return httpClient;
    }
/*

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }

                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
*/

    /**
     * 获取Cookie方法
     *
     * @param request request对象
     * @param name    Cookie名称
     * @return 值
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
     * @param request 请求
     * @return ua
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
            return URLEncoder.encode(value, charsetName);
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
            if ("1".equals(value)) {
                return Boolean.FALSE;
            } else if ("0".equals(value)) {
                return Boolean.TRUE;
            } else {
                throw new RuntimeException("数字只能传入1或者0可转换boolean");
            }
        }
        return Boolean.valueOf(value);
    }


}


