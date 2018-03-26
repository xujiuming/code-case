package com.ming.core.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.ming.api.config.AliyunBaseConfig;
import com.ming.api.config.AliyunIPConfig;
import com.ming.api.config.AliyunWeatherConfig;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * aliyun 相关 api接口信息
 *
 * @author ming
 * @date 2017-06-25
 */

public class AliyunAPIUtils {

    /**
     * aliyun 简单身份验证 appcode 通用headers
     *
     * @author ming
     * @date 2017-06-25
     */
    private static Map<String, String> headers = ImmutableMap.of("Authorization", "APPCODE " + AliyunBaseConfig.appcode);

    /**
     * 获取ip信息
     * aliyun ip api
     * 详细api文档：https://market.aliyun.com/products/57002003/cmapi010805.html#sku=yuncode480500000
     *
     * @param ip ip地址
     * @author ming
     * @date 2017-06-24
     */
    public static String getIpInfo(String ip) throws Exception {
        //验证ip 是否合法
        {
            if (!(RegexUtils.isIP(ip))) {
                // throw new ServiceException("调用ip api ip不合法！！");
            }
        }
        Map<String, String> querys = new HashMap<>();
        querys.put("ip", ip);
        return EntityUtils.toString(HttpUtils.doGet(AliyunIPConfig.host, AliyunIPConfig.path, headers, querys).getEntity());
        //System.out.println(response.toString());
        //获取response的body
        //System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 获取 aliyun  ip接口的参数信息
     *
     * @author ming
     * @date 2017-06-25
     */
    public static String ipConfigToJsonString() {
        return JSON.toJSONString(ImmutableMap.of("host", AliyunIPConfig.host, "path", AliyunIPConfig.path, "appcode", AliyunIPConfig.appcode));
    }


    /**
     * 获取 天气情况
     * https://market.aliyun.com/products/56928004/cmapi014123.html#sku=yuncode812300000
     *
     * @param area 地区名称
     * @author ming
     * @date 2017-06-25
     */
    public static String getWeather(String area) throws Exception {
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", area);
        HttpResponse response = HttpUtils.doGet(AliyunWeatherConfig.host, AliyunWeatherConfig.path, headers, querys);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 获取 aliyun  天气接口的参数信息
     *
     * @author ming
     * @date 2017-06-25
     */
    public static String weatherConfigToJsonString() {
        return JSON.toJSONString(ImmutableMap.of("host", AliyunIPConfig.host, "path", AliyunIPConfig.path, "appcode", AliyunIPConfig.appcode));
    }

}





