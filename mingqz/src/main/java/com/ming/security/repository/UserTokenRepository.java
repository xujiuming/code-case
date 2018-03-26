package com.ming.security.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.security.entity.UserToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTokenRepository extends BaseRepository<UserToken, Long> {

    List<UserToken> findByUserIdAndStatusNot(Long userId, Integer status);

    UserToken findUserTokenByUserIdAndToken(Long userId,String token);
    UserToken findUserTokenByToken(String token);

    List<UserToken> findAllByStatus(Integer status);



}
