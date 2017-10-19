package com.ming;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class StartConfig {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StartConfig.class).web(true).run(args);
    }
}
