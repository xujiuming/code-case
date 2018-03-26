package com.ming.security.entity;


import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * @author ming
 * @date 2018-01-06 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserToken extends InId {

    private Long userId;
    private String token;
    private Integer status;


    @Getter
    public enum Status {

        /**
         * 启用
         */
        ENABLE(1, "ENABLE"),
        /**
         * 禁用
         */
        DISABLE(2, "DISABLE"),
        /**
         * 已删除
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
