package com.ming.security.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.security.entity.UserGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends BaseRepository<UserGroup,Long> {
}
