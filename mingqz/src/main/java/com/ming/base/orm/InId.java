package com.ming.base.orm;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 继承映射 基类
 * 只允许long 类型主键  不允许 其他类型
 *
 * @author ming
 * @date 2017-11-10 11:07
 */
@MappedSuperclass
@Data
public class InId implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long lastUpdateTimeMillis = System.currentTimeMillis();

    private Long createTimeMillis;


}
