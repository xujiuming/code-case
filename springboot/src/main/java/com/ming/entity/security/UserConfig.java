package com.ming.entity.security;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 用户与资源权限配置表关联配置
 *
 * @author ming
 * @date 2017-11-06 15:21
 */
@Entity
@Data
public class UserConfig extends InId {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户组id
     */
    private Long userGroupId;
    /**
     * 资源权限配置id
     */
    private String uuid;
}
