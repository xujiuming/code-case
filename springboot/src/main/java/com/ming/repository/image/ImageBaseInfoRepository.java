package com.ming.repository.image;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.entity.image.ImageBaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ImageBaseInfoRepository extends BaseRepository<ImageBaseInfo, Long> {

    Collection<ImageBaseInfo> findAllByMd5In(Collection<String> md5Collection);

    <T> Page<ImageBaseInfo> findAll(Specification<T> spec, Pageable pageable);
}
