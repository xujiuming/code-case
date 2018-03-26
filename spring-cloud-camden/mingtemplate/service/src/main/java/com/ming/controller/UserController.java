package com.ming.controller;

import com.ming.entity.User;
import com.ming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("test")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user, HttpServletRequest request) {
        return 0;
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return null;
    }

    @RequestMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Object findAllUser() {
        return userService.findAllUser();
    }
}
