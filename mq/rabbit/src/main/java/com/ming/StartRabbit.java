package com.ming;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *rabbit mq消息处理模块 启动类
 *@author ming
 *@date 2017-10-30 11:21
 */
@SpringBootApplication
public class StartRabbit {

    public static void main(String[] args) {
        SpringApplication.run(StartRabbit.class,args);
    }
}
