package com.ming.timer.base.orm;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 继承映射父类  所有entity 继承这个类
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@MappedSuperclass
@Data
public class InId implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date gmtCreate;
    private Date gmtModified = new Date();
    private Boolean isDeleted = Boolean.FALSE;
}
