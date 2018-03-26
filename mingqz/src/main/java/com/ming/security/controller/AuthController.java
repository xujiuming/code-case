package com.ming.security.controller;

import com.ming.base.BaseController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页Controller
 *
 * @author ming
 * @date 2017-12-12 10:29
 */
@RestController
public class AuthController extends BaseController {

    /**
     * 首页
     *
     * @param modelMap
     * @return
     * @author ming
     * @date 2017-12-12 10:30
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(ModelMap modelMap) {
        return "index";
    }

    /**
     * 登录页面
     *
     * @return page
     * @author ming
     * @date 2017-12-12 10:30
     */
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login() {
        return "login";
    }

    /**
     * 登录
     *
     * @param modelMap
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 记住我
     * @param request
     * @return code
     * @author ming
     * @date 2017-12-12 10:30
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(ModelMap modelMap,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("rememberMe") Boolean rememberMe,
                        HttpServletRequest request) {
        return "";
    }

    /**
     * 未授权
     *
     * @return page
     * @author ming
     * @date 2017-12-12 10:30
     */
    @GetMapping(value = "/unauthorized", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String unauthorized() {
        return "403";
    }

    /**
     * 登出
     *
     * @return page
     */
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String logout() {
        return "redirect:/";
    }

    /**
     * 健康检查url
     *
     * @return
     * @author ming
     * @date 2017-12-12 10:30
     */
    @GetMapping(value = "/health-check/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String healthCheck() {
        return "ok";
    }

}
