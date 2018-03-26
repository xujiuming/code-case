package com.ming.security.controller;


import com.ming.base.BaseController;
import com.ming.security.controller.vo.LoginVO;
import com.ming.security.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming
 * @date 2018-01-06 17:07
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * login
     *
     * @author ming
     * @date 2018-01-06 17:08
     */
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginVO login(LoginVO loginVO) {
        return userService.login(loginVO);

    }


    /**
     * open login
     *
     * @author ming
     * @date 2018-01-06 17:17
     */
    @PostMapping(value = "open-login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginVO openLogin() {
        return null;
    }

}
