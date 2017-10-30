package com.ming;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心 实例 2
 *
 * @author ming
 * @date 2017-10-30 11:19
 */
@EnableEurekaServer
@SpringBootApplication
public class StartEurekaServer2 {

    public static void main(String[] args) {
        //SpringApplication.run()
        new SpringApplicationBuilder(StartEurekaServer2.class).web(true).run(args);
    }
}
