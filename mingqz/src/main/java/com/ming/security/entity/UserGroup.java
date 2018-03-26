package com.ming.security.entity;


import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * user group info
 *
 * @author ming
 * @date 2018-01-06 16:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserGroup extends InId {

    /**
     * uuid
     * @see User#uuid
     */
    private String uuid;
    /**
     * user group name
     */
    private String name;
    /**
     * user group memo
     */
    private String memo;

    /**
     * user group status
     * {@link Status}
     */
    private String status;

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
