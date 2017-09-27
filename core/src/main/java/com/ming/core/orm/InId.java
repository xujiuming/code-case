package com.ming.core.orm;

import lombok.Data;

import java.io.Serializable;

/**
 * 继承映射父类  所有entity 继承这个类 使用long类型id
 *
 * @author ming
 * @date 2017-08-28 11点
 */
//@MappedSuperclass
@Data
public class InId implements Serializable {


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lastUpdateTimeMillis = System.currentTimeMillis();

    private Long createTimeMillis;


}