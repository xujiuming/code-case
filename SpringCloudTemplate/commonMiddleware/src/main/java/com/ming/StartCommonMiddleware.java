package com.ming;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * support module  start class
 * 开启eureka server功能
 * 开启admin server功能
 * 本身自己提供自身监控
 * 注册本身服务
 *
 * @author ming
 * @date 2020-08-11 14:44
 */
@SpringBootApplication
@EnableEurekaServer
@EnableAdminServer
@EnableDiscoveryClient
public class StartCommonMiddleware {
    public static void main(String[] args) {
        SpringApplication.run(StartCommonMiddleware.class, args);
    }

    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and()
                    .httpBasic().disable()
                    .csrf().disable()
            ;
        }
    }
}
