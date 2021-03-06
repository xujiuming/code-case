package com.ming.entity;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 继承映射父类  所有entity 继承这个类
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@MappedSuperclass
@Data
public class InId implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lastUpdateTimeMillis = System.currentTimeMillis();

    private Long createTimeMillis;


}
