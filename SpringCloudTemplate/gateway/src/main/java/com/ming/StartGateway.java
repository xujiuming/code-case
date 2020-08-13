package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * gateway启动
 *
 * @author ming
 * @date 2020-08-13 15:27
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartGateway {
    public static void main(String[] args) {
        SpringApplication.run(StartGateway.class, args);
    }
}
