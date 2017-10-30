package com.ming.api.service;

import com.ming.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务 接口
 *
 * @author ming
 * @date 2017-10-30 11:21
 */
@RestController
@RequestMapping("user")
public interface UserService {

    /**
     * 根据id 获取用户信息
     *@param id
     *@author ming
     *@date 2017-10-30 11:31
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    User findById(@RequestParam("id") Long id);

}
