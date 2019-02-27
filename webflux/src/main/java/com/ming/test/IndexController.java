package com.ming.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author ming
 * @date 2019-02-26 17:29:21
 */
@RestController
public class IndexController {

    @GetMapping(value = {"","/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "exception")
    public String exception(){
        throw new RuntimeException("dafsafasdjkla");
    }
}
