package com.ming.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    //服务降级 顶级服务---》次级服务 v2--------》最低级别v1
    //设定 这个服务使用熔断机制  熔断回调方法 是v2  忽略runtimeexception
    @HystrixCommand(fallbackMethod = "v2", ignoreExceptions = RuntimeException.class)
    public String userService() {
        return restTemplate.getForObject("http://USER-SERVICE/account/login?username=ming", String.class);
    }

    @HystrixCommand(fallbackMethod = "v1")
    public String v2(Throwable e) throws InterruptedException {
        //降级
        //Thread.sleep(30000);
        return "v2级别 降级" + e.getMessage();
    }

    public String v1(Throwable e) {
        return "v1级别 最终级别 熔断" + e.getMessage();
    }
}
