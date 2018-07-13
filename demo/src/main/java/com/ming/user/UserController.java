package com.ming.user;

import com.ming.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private TestCacheService testCacheService;

    @GetMapping("user")
    public String getUser(){
        return "ming";
    }

    @GetMapping("test-cache")
    public String getTestCache(String id){
      return   testCacheService.testCache(id);
    }
}
