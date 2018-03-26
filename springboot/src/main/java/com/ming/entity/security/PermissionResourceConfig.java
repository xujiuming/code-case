package com.ming.entity.security;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 资源权限配置表
 *
 * @author ming
 * @date 2017-11-06 15:21
 */
@Entity
@Data
public class PermissionResourceConfig extends InId {


    /**
     * uuid 用户或者用户组 通过uuid查询资源权限信息
     * 第二主键
     */
    private String uuid;
    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 操作类型
     * {@linkplain PermissionResourceConfig.Type#type}
     */
    private Integer type;

    /**
     * 状态
     * {@linkplain PermissionResourceConfig.Status#status}
     */
    private Integer status;


    enum Status {
        D(1);
        private Integer status;

        Status(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    enum Type {
        /**
         * 详情
         */
        DETAIL(1, "详情"),
        /**
         * 新增
         */
        CREATE(2, "创建"),
        /**
         * 修改
         */
        UPDATE(3, "修改"),
        /**
         * 可删除
         */
        DELETE(4, "删除");


        private Integer type;
        private String name;

        Type(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
