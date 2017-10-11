package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//开启注册
@EnableDiscoveryClient
//开启熔断器
@EnableCircuitBreaker
@SpringBootApplication
//一个顶上面三个
//@SpringCloudApplication
public class StartCommon {

    public static void main(String[] args) {
        SpringApplication.run(StartCommon.class, args);
    }

}
