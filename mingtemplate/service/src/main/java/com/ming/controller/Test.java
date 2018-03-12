package com.ming.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.StrTokenizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Log4j2
public class Test {
    @GetMapping("get")
    public String get() {
        return "12afsjkldfa";
    }

    @GetMapping("log")
    public String log(){
        for (int i = 0; i < 100000; i++) {
            log.debug("debug");
            log.error("mingerror");
            log.info("info");
        }
        return "000";
    }
    @GetMapping("get1")
    public String get1() {
        return "22222222222222222222";
    }

}
