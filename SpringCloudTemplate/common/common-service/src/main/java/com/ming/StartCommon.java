package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * common module start class
 *
 * @author ming
 * @date 2020-08-11 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StartCommon {

    public static void main(String[] args) {
        SpringApplication.run(StartCommon.class, args);
    }
}
