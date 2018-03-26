package com.ming.security.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.security.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends BaseRepository<Resource,Long> {
}
