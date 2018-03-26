package com.ming.security.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.security.entity.ResourceReal;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ResourceRealRepository extends BaseRepository<ResourceReal,Long> {


    List<ResourceReal> findByUuidInAndAndStatusEquals(Collection<String> uuidCollection,Integer status);
}
