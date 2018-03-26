package com.ming.security.service.impl;

import com.google.common.base.Preconditions;
import com.ming.base.BaseService;
import com.ming.core.utils.CacheUtils;
import com.ming.security.controller.vo.LoginVO;
import com.ming.security.entity.User;
import com.ming.security.repository.UserRepository;
import com.ming.security.service.api.UserService;
import com.ming.security.service.api.UserTokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 *
 * @author ming
 * @date 2017-12-12 10:31
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public LoginVO login(LoginVO loginVO) {
        Preconditions.checkNotNull(loginVO.getUsername(), "username not null ");
        Preconditions.checkNotNull(loginVO.getPassword(), "password not null");
        User user = userRepository.findByUsernameAndPassword(loginVO.getUsername(), loginVO.getPassword());
        String token = DigestUtils.md5Hex(user.getUsername() + user.getPassword() + System.currentTimeMillis());
        userTokenService.putToken(user.getId(), token);
        //  out  token
        CacheUtils.tokenCache.put(user.getId(), token);
        // result
        LoginVO result = new LoginVO();
        result.setUsername(user.getUsername());
        result.setName(user.getName());
        result.setHeadImageUrl(user.getHeadImageUrl());
        result.setToken(token);
        return result;
    }

    @Override
    public User findUserByLoginName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
