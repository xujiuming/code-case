package com.ming.entity;

import com.ming.core.orm.InId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDict extends InId<Long> {

    /**
     * 名称
     * */
    private String name;
    /**
     * 值
     * */
    private String value;
    /**
     * 备注
     * */
    private String memo;
    /**
     * 字典类型
     * {@linkplain Type}
     */
    private Integer type;

    /**
     * 状态
     * */
    private Integer status;

    /**
     * 父级id
     * */
    private Long parentId;



    enum Type {
        /**
         * 系统类型
         * */
        SYSTEM(1);
        private Integer type;

        Type(Integer type) {
            this.type = type;
        }
    }

    enum Status{
        /**
         * 可用
         * */
        VALIAD(1),
        /**
         * 不可用
         * */
        INVALID(2);

        private Integer status;

        Status(Integer status) {
            this.status = status;
        }
    }


    public DataDict(Long id,String name) {
        super.setId(id);
        this.name = name;
    }
}
