package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 *集群监控 熔断器 启动类
 *@author ming
 *@date 2017-10-30 11:21
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class StartTurbine {

    public static void main(String[] args) {
        SpringApplication.run(StartTurbine.class, args);
    }
}
