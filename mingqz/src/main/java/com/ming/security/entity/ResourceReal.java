package com.ming.security.entity;

import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;

/**
 * uuid 和resource 关系表
 *
 * @author ming
 * @date 2018-01-18 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ResourceReal extends InId {

    /**
     * uuid
     * {@linkplain UserGroup#uuid}
     */
    private String uuid;


    /**
     * resource id
     * {@linkplain Resource#id}
     */
    private Long resourceId;

    /**
     * source  type
     * {@link Type}
     */
    private Integer type;


    /**
     * status
     * {@link Status}
     */
    private Integer status;


    @Transient
    private Resource resource;


    @Getter
    public enum Status {

        /**
         * 启用
         */
        ENABLE(1, "ENABLE"),
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


    @Getter
    public enum Type {
        USER(1, "user"),
        USER_GROUP(2, " user group");


        private Integer type;
        private String name;

        Type(Integer type, String name) {
            this.type = type;
            this.name = name;
        }
    }
}
