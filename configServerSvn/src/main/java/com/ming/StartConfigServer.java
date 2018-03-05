package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/** config server start class
 *
 * @author  ming
 * @date  2017-12-06 10:08
 * */
@EnableConfigServer
@SpringBootApplication
public class StartConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(StartConfigServer.class,args);
    }
}
