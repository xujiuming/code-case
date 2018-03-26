package com.ming.security.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.security.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author ming
 * @date 2017-11-09 18:09
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 根据姓名查询用户信息
     *
     * @param userName
     * @return User
     * @author ming
     * @date 2017-12-12 10:27
     */
    User findByUsername(String userName);

    User findByUsernameAndPassword(String userName, String password);
}
