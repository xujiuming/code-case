package com.ming.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserColtroller {


    @GetMapping("user")
    public String getUser(){
        return "ming";
    }

}
