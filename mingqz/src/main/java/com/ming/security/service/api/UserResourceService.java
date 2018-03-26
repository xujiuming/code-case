package com.ming.security.service.api;

import com.ming.security.entity.ResourceReal;

import java.util.List;

/**
 * 用户资源服务
 *
 * @author ming
 * @date 2018-01-18 15:53
 */
public interface UserResourceService {


    /**
     * 根据用户id 查询 用户的 uri 资源信息
     *
     * @author ming
     * @date 2018-01-18 15:54
     */
    List<ResourceReal> findUserUriResourceByUserId(Long userId);

    /**
     * 根据用户id 查询 用户的 视图 资源信息
     *
     * @author ming
     * @date 2018-01-18 15:54
     */
    List<ResourceReal> findUserViewResourceByUserId(Long userId);

    /**
     * 根据用户id 查询 用户的 数据 资源信息
     *
     * @author ming
     * @date 2018-01-18 15:54
     */
    List<ResourceReal> findUserDataResourceByUserId(Long userId);
}
