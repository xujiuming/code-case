package com.ming.controller.user;

import com.ming.entity.user.User;
import com.ming.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "create")
    public boolean create(User user) {
        userService.save(user);
        return true;
    }


    @GetMapping(value = "detail")
    @ResponseBody
    public List<User> detail() {
        return userService.findUsers();
    }
}
