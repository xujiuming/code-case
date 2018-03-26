package com.ming.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * aliyun ip接口的 参数注入
 *
 * @author ming
 * @date 2017-06-25
 */
@Component
@ConfigurationProperties(prefix = "aliyun.ip")
public class AliyunIPConfig extends AliyunBaseConfig {
    public static String host;
    public static String path;

    public String getHost() {
        return AliyunIPConfig.host;
    }

    public void setHost(String host) {
        AliyunIPConfig.host = host;
    }

    public String getPath() {
        return AliyunIPConfig.path;
    }

    public void setPath(String path) {
        AliyunIPConfig.path = path;
    }

}