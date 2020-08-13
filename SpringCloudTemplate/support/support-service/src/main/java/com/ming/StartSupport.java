package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * support module  start class
 * 开启eureka server功能
 * 开启admin server功能
 * 本身自己提供自身监控
 * 注册本身服务
 *
 * @author ming
 * @date 2020-08-11 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartSupport {
    public static void main(String[] args) {
        SpringApplication.run(StartSupport.class, args);
    }

}
