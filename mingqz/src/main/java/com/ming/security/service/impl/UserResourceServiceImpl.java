package com.ming.security.service.impl;

import com.ming.base.BaseService;
import com.ming.core.constans.CacheConstans;
import com.ming.security.entity.Resource;
import com.ming.security.entity.ResourceReal;
import com.ming.security.entity.User;
import com.ming.security.entity.UserGroup;
import com.ming.security.repository.ResourceRealRepository;
import com.ming.security.repository.ResourceRepository;
import com.ming.security.repository.UserGroupRepository;
import com.ming.security.repository.UserRepository;
import com.ming.security.service.api.UserResourceService;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = CacheConstans.USER_RESOURCE)
public class UserResourceServiceImpl extends BaseService implements UserResourceService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceRealRepository resourceRealRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;


    @Override
    @Cacheable(key = "#userId")
    public List<ResourceReal> findUserUriResourceByUserId(Long userId) {
        User user = userRepository.findOne(userId);
        UserGroup userGroup = userGroupRepository.findOne(user.getUserGroupId());
        Set<String> uuidSet = Sets.newHashSet();
        uuidSet.add(user.getUuid());
        uuidSet.add(userGroup.getUuid());
        List<ResourceReal> resourceRealList = resourceRealRepository.findByUuidInAndAndStatusEquals(uuidSet,ResourceReal.Status.ENABLE.getStatus());
        Set<Long> resourceIdSet = Sets.newHashSet();
        resourceIdSet.addAll(resourceRealList.stream().map(ResourceReal::getResourceId).collect(Collectors.toSet()));
        List<Resource> resources = resourceRepository.findListByIds(resourceIdSet,new Resource());

        return null;
    }

    @Override
    @Cacheable(key = "#userId")
    public List<ResourceReal> findUserViewResourceByUserId(Long userId) {
        return null;
    }

    @Override
    @Cacheable(key = "#userId")
    public List<ResourceReal> findUserDataResourceByUserId(Long userId) {
        return null;
    }
}
