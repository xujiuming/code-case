package com.ming.core.orm;

import lombok.Data;

import java.io.Serializable;

/**
 * 继承映射父类  所有entity 继承这个类  无特殊需求 尽量使用Long
 *
 * @author ming
 * @date 2017-08-28 11点
 */
//@MappedSuperclass
@Data
public class InId<T extends Number> implements Serializable {


   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    private Long lastUpdateTimeMillis = System.currentTimeMillis();

    private Long createTimeMillis;


}