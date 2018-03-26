package com.ming.security.service.api;

import com.ming.security.controller.vo.LoginVO;
import com.ming.security.entity.User;


/**
 * 用户服务接口
 *
 * @author ming
 * @date 2017-12-12 10:29
 */
public interface UserService {


    /**
     * login
     *
     * @param loginVO
     * @return LoginVO
     * @author ming
     * @date 2018-01-06 17:10
     */
    LoginVO login(LoginVO loginVO);

    /**
     * 根据登陆用户名查询用户信息
     *
     * @param userName
     * @return User
     * @author ming
     * @date 2017-12-12 10:28
     */
    User findUserByLoginName(String userName);
}
