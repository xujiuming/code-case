package com.ming.repository.user;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
