package com.ming.security.entity;

import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 用户
 * Date:  17/7/12 下午6:05
 *
 * @author ming
 * @date 2017-12-12 10:31
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends InId {

    /**
     * login user name
     */
    private String username;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * user head image url
     */
    private String headImageUrl;
    /**
     * 密码
     */
    private String password;

    /**
     * uuid
     * @see UserGroup#uuid
     * */
    private String uuid;

    /**
     * user group id
     * {@linkplain UserGroup#id}
     */
    private Long userGroupId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户状态
     */
    private Integer status;


    @Getter
    public enum Status {

        /**
         * 用户状态 -- 启用
         */
        ENABLE(1, "ENABLE"),
        /**
         * 用户状态 -- 禁用
         */
        DISABLE(2, "DISABLE"),
        /**
         * 用户状态 -- 已删除
         */
        DELETE(3, "DELETE");

        private Integer status;
        private String statusName;

        Status(Integer status, String statusName) {
            this.status = status;
            this.statusName = statusName;
        }
    }

}
