package com.ming.security.service.impl;

import com.ming.base.BaseService;
import com.ming.core.constans.CacheConstans;
import com.ming.core.utils.CollectionUtils;
import com.ming.security.entity.UserToken;
import com.ming.security.repository.UserTokenRepository;
import com.ming.security.service.api.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user token 服务  根据token 缓存  缓存对象只有 userid 和token信息
 * 用户通过此服务 查询用户id  通过用户id 从 权限服务 获取相关资源权限
 *
 * @author ming
 * @date 2018-01-18 14:43
 */
@Service
@CacheConfig(cacheNames = CacheConstans.USER_TOKEN)
public class UserTokenServiceImpl extends BaseService implements UserTokenService {
    @Autowired
    private UserTokenRepository userTokenRepository;

    /**
     * 添加token信息  并且根据token 来进行缓存
     *
     * @param userId
     * @param token
     * @author ming
     * @date 2018-01-18 14:33
     */
    @Override
    @Cacheable(key = "#token")
    public UserToken addToken(Long userId, String token) {
        List<UserToken> userTokens = userTokenRepository.findByUserIdAndStatusNot(userId, UserToken.Status.ENABLE.getStatus());
        if (CollectionUtils.notEmpty(userTokens)) {
            // userTokenRepository.updateStatusByUserId(userId);
        }
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        userToken.setStatus(UserToken.Status.ENABLE.getStatus());
        userToken.setCreateTimeMillis(System.currentTimeMillis());
        return userTokenRepository.save(userToken);
    }


    @Override
    @CacheEvict(key = "#token")
    public void removeTokenByUserIdAndToken(Long userId, String token) {
        UserToken userToken = userTokenRepository.findUserTokenByUserIdAndToken(userId, token);
        userToken.setStatus(UserToken.Status.DELETE.getStatus());
        userTokenRepository.save(userToken);
    }

    @Override
    @CacheEvict(key = "#token")
    public void removeTokenByToken(String token) {
        UserToken userToken = userTokenRepository.findUserTokenByToken(token);
        userToken.setStatus(UserToken.Status.DELETE.getStatus());
        userTokenRepository.save(userToken);
    }


    @Override
    @CacheEvict(allEntries = true)
    public void removeAllToken() {
        List<UserToken > userTokenList = userTokenRepository.findAllByStatus(UserToken.Status.ENABLE.getStatus());
        // 修改对象  内存地址是不变的 所以是可以修改的
        userTokenList.forEach(u-> u.setStatus(UserToken.Status.DELETE.getStatus()));
        userTokenRepository.save(userTokenList);
    }
}
