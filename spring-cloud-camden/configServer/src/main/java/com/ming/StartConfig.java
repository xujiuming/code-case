package com.ming;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * config server 启动类
 *
 * @author ming
 * @date 2017-10-30 11:18
 */
//开启config 服务
@EnableConfigServer
//注册到注册中心
@EnableDiscoveryClient
@SpringBootApplication
public class StartConfig {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StartConfig.class).web(true).run(args);
    }
}
