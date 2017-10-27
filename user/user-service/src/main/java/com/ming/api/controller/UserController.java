package com.ming.api.controller;

import com.ming.api.service.client.CommonServiceClient;
import com.ming.entity.DataDict;
import com.ming.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private CommonServiceClient.DataDictServiceClient dictServiceClient;
    @Autowired
    private CommonServiceClient.LogServiceClient logServiceClient;
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(String username){
        return username;
    }

    @RequestMapping(value = "dict",method = RequestMethod.GET)
    public DataDict dict(Long id){
        return dictServiceClient.findDictById(id);
    }

    @RequestMapping(value = "log",method = RequestMethod.GET)
    public Log log(Long id){
        return logServiceClient.findLogById(id);
    }


    @Value("${from}")
    private String from;


    @RequestMapping(value = "from",method = RequestMethod.GET)
    public String getFrom(){
        return from;
    }
}
