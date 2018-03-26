package com.ming.entity.image;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 图片样式表
 *
 * @author ming
 * @date 2017-11-06 15:24
 */
@Entity
@Data
public class ImageStyle extends InId {

    /**
     * 样式名称
     */
    private String name;
    /**
     * 样式符号规则
     */
    private String symbolRule;


}
