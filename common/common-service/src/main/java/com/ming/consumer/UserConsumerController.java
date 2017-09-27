package com.ming.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserConsumerController  {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/common")
    public String common(){
        System.out.println(1111111);
        return restTemplate.getForEntity("http://USER-SERVICE/account/login?username=ming"+System.currentTimeMillis(),String.class).getBody();
    }

}
