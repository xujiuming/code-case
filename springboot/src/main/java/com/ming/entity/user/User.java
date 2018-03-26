package com.ming.entity.user;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class User extends InId {

    private String username;
    private String password;

    /**
     * uuid 用来匹配资源
     */
    private String uuid;

}
