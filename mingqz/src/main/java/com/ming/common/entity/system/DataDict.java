package com.ming.common.entity.system;

import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * 数据字典详情表
 *
 * @author ming
 * @date 2017-11-10 11:05
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class DataDict extends InId {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 父 id
     * {@link DataDict#id}
     */
    private Long parentId;

    /**
     * 类型 用在何处
     * {@link Type}
     */
    private Integer type;


    @Getter
    public enum Type {
        /**
         * 系统配置性参数
         */
        SYSTEM(1, "系统参数"),
        /**
         * 页面使用参数
         */
        VIEW(2, "页面参数"),
        /**
         * 代码中使用参数
         */
        CODE(3, "代码使用");

        private Integer key;
        private String value;

        Type(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

}
