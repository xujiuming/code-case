package com.ming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author com.ming
 * @date 2018-01-24 10:18
 */
@SpringBootApplication
@MapperScan(value = {"com.**.mapper"})
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
