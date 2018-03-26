package com.ming.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * aliyun 天气接口的 参数注入
 *
 * @author ming
 * @date 2017-06-25
 */
@Component
@ConfigurationProperties(prefix = "aliyun.weather")
public class AliyunWeatherConfig extends AliyunBaseConfig {
    public static String host;
    public static String path;

    public String getHost() {
        return AliyunWeatherConfig.host;
    }

    public void setHost(String host) {
        AliyunWeatherConfig.host = host;
    }

    public String getPath() {
        return AliyunWeatherConfig.path;
    }

    public void setPath(String path) {
        AliyunWeatherConfig.path = path;
    }

}