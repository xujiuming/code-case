package com.ming.base;

import com.ming.base.config.OssConfig;
import com.ming.core.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/*
* 系统环境
* */
@Component
public class ApplicationConfig {
    public static String endpoint;
    public static String accessKeyId;
    public static String accessKeySecret;
    public static String bucket;
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private OssConfig ossConfig;

   /* @PostConstruct
    public synchronized void init() {
        endpoint = ossConfig.getEndpoint();
        accessKeyId = ossConfig.getAccessKeyId();
        accessKeySecret = ossConfig.getAccessKeySecret();
        bucket = ossConfig.getBucket();
    }*/

    /**
     * 初始化系统参数
     */
    @PostConstruct
    public synchronized static void init() {
        System.setProperty("jsse.enableSNIExtension", "false");// 防止HttpGet请求报错(javax.net.ssl.SSLProtocolException: handshake alert: unrecognized_name)
        System.setProperty("https.protocols", "SSLv3,SSLv2Hello"); // 解决：java.lang.RuntimeException: javax.net.ssl.SSLException: Received fatal alert: bad_record_mac

        long now = System.currentTimeMillis();
        LOGGER.info("初始ApplicationConfig开始...");
        //这里jar包启动加载外部配置文件时,要用spring方式加载
        PropertiesLoader loader;
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("application-aliyun.yml"), "UTF-8"));
            loader = new PropertiesLoader(props);
        } catch (IOException e) {
            throw new Error("加载配置文件[%s]失败");
        }
        if (loader.getProperties().isEmpty()) {
            throw new Error("配置文件[%s]为空");
        }
        endpoint = loader.getProperty("endpoint");
        accessKeyId = loader.getProperty("accessKeyId");
        accessKeySecret = loader.getProperty("accessKeySecret");
        bucket = loader.getProperty("bucket");

        LOGGER.info("初始化ApplicationConfig完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }
}
