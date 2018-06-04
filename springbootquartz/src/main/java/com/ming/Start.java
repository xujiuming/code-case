package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 调度中心启动类
 *
 * @author onlyedu.onlyedu.onlyedu
 * @date 2018-03-13 14:40
 */
@SpringBootApplication
@EnableSwagger2
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
