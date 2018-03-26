package com.ming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = {"index","","/"})
    public String index(){
        return "config server ";
    }
}
