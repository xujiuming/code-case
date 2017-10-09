package com.ming.consumer;

import com.ming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserConsumerController {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/common")
    public String common() throws InterruptedException {
        System.out.println(1111111);
        return userService.userService();
    }

}
