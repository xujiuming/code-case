package com.ming;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class StartEurekaServer1 {

    public static void main(String[] args) {
        //SpringApplication.run()
        new SpringApplicationBuilder(StartEurekaServer1.class).web(true).run(args);
    }
}
