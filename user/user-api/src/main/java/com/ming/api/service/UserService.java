package com.ming.api.service;

import com.ming.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public interface UserService {

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    User findById(@RequestParam("id")Long id);

}
