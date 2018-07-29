package com.ming.timer.base.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * Created by xianyu on 17-9-24.
 */
public interface BaseControllerInterface {

    Pageable buildPageable(int pageNo, int pageSize, Sort sort);

    Pageable buildPageable(int pageNo, int pageSize);

    Pageable buildPageable(int pageNo);

    Pageable buildPageable(int PageNo, Sort sort);
}
