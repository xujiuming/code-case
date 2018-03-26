package com.ming.base.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * Created by xianyu on 17-9-24.
 */
public abstract class SupportAbstractService implements SupportServiceInterface {


    @Override
    public Pageable buildPageable(int pageNo, int pageSize, Sort sort) {
        return new PageRequest(pageNo, pageSize, sort);
    }

    @Override
    public Pageable buildPageable(int pageNo, int pageSize) {
        return this.buildPageable(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
    }

    @Override
    public Pageable buildPageable(int pageNo) {
        return this.buildPageable(pageNo, 15);
    }

    @Override
    public Pageable buildPageable(int pageNo, Sort sort) {
        return this.buildPageable(pageNo, 15, sort);
    }
}
