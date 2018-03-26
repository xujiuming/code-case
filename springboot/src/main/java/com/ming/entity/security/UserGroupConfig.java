package com.ming.entity.security;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 用户组与资源权限配置关联表
 *
 * @author ming
 * @date 2017-11-06 15:22
 */
@Entity
@Data
public class UserGroupConfig extends InId {

    /**
     * 用戶組名称
     */
    private String name;


    /**
     * uuid 用来匹配资源
     */
    private String uuid;

}
