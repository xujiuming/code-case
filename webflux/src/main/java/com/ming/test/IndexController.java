package com.ming.test;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 *
 * @author ming
 * @date 2019-02-26 17:29:21
 */
@RestController
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"","/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "exception")
    public String exception(){
        throw new RuntimeException("dafsafasdjkla");
    }

    @GetMapping(value = "/test")
    public Map<String,Object> test(){
        Map<String,Object> result  = Maps.newHashMap();
        result.put("String","mings");
        result.put("Integer",112221);
        result.put("Boolean",Boolean.TRUE);
        result.put("LocalDateTime", LocalDateTime.now());
        result.put("LocalDate", LocalDate.now());
        result.put("LocalTime", LocalTime.now());
        return result;
    }


    @GetMapping(value = "/user/list")
    public List<User> userList (){
        return  userRepository.findAll();
    }

    @PostMapping(value = "/user")
    public User addUser(User user){
        return userRepository.save(user);
    }

    @PutMapping(value = "/user")
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public Boolean deletetUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
