package com.ming.controller.system;

import com.ming.base.annotations.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * http请求 错误页面重定向控制器
 *
 * @author ming
 * @date 2017-09-02
 */
@Controller
@RequestMapping(value = "error")
public class HttpErrorController {


    @GetMapping(value = "400")
    public String error400() {
        return "error/400";
    }

    @GetMapping(value = "404")
    public String error404() {
        return "error/404";
    }


    @GetMapping(value = "500")
    public String error500() {
        return "error/500";
    }


}
