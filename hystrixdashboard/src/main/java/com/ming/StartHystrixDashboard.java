package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class StartHystrixDashboard {

    public static void main(String[] args) {
        SpringApplication.run(StartHystrixDashboard.class, args);
    }

}
