package com.ming.security.service.api;


import com.ming.security.entity.UserToken;

/**
 * usertoken服务  只允许add 和remove  不允许 update操作
 *
 * @author ming
 * @date 2018-01-18 14:37
 */
public interface UserTokenService {

    /**
     * 添加token
     *
     * @author ming
     * @date 2018-01-18 15:01
     */
    UserToken addToken(Long userId, String token);

    /**
     * 根据用户id 和token 删除 token信息
     *
     * @author ming
     * @date 2018-01-18 15:01
     */
    void removeTokenByUserIdAndToken(Long userId, String token);

    /**
     * 根据token删除 token信息
     *
     * @author ming
     * @date 2018-01-18 15:02
     */
    void removeTokenByToken(String token);

    /**
     * 强制下线所有token
     *
     * @author ming
     * @date 2018-01-18 14:42
     */
    void removeAllToken();
}
