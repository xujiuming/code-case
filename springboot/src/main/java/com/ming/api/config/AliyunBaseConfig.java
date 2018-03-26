package com.ming.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunBaseConfig {

    public static String appcode;

    public String getAppcode() {
        return AliyunBaseConfig.appcode;
    }

    public void setAppcode(String appcode) {
        AliyunBaseConfig.appcode = appcode;
    }
}
