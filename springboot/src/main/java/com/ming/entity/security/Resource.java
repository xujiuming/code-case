package com.ming.entity.security;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 资源表
 *
 * @author ming
 * @date 2017-11-06 15:21
 */
@Entity
@Data
public class Resource extends InId {

    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源内容
     */
    private String content;
    /**
     * 资源类型
     */
    private Integer type;


    enum Type {
        MENU(1, "菜单"),
        BUTTON(2, "按钮"),
        URI(3, "uri访问权限"),
        DATA(4, "数据访问权限"),;

        private Integer type;
        private String name;

        Type(Integer type, String name) {
            this.type = type;
            this.name = name;
        }
    }

}
