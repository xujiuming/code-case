package com.ming.security.entity;


import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * resource info
 *
 * @author ming
 * @date 2018-01-06 16:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Resource extends InId {

    /**
     * resource name
     */
    private String name;

    /**
     * code or url
     */
    private String resourceCode;
    /**
     * resource type
     * {@link Type}
     */
    private Integer type;


    /**
     * resource status
     * {@link Status}
     */
    private Integer status;


    @Getter
    public enum Type {
        VIEW(1, "视图资源"),
        URL(2, "url 资源"),
        DATA(3, "数据资源");


        private Integer type;
        private String name;
        ;

        Type(Integer type, String name) {
            this.type = type;
            this.name = name;
        }
    }

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
