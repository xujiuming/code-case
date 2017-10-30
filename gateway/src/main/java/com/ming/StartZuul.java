package com.ming;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 *zuul 路由 启动类
 *@author ming
 *@date 2017-10-30 11:20
 */
@EnableZuulProxy
@SpringBootApplication
public class StartZuul {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StartZuul.class).web(true).run(args);
    }


    /**设定自动生成路由规则
    *@author ming
    *@date 2017-10-19 15:12
    */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<service>v.+$)"
                ,"/${service}-${name}");
    }
}
