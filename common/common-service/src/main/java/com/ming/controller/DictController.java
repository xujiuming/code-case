package com.ming.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dict")
public class DictController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "all")
    public String all(String username) {
        System.out.println(username);
        //长时间休眠  触发熔断
        //Thread.sleep(30000);
        return username + JSON.toJSONString(discoveryClient.getLocalServiceInstance());
    }
}
