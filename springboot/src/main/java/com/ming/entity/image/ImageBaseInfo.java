package com.ming.entity.image;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 图片基本信息表 从oss拉去下来的数据
 *
 * @author ming
 * @date 2017-11-06 15:25
 */
@Entity
@Data
public class ImageBaseInfo extends InId {

    private String name;
    /**
     * 图片url   域名+name  不带style
     */
    private String url;

    /**
     * md5
     */
    private String md5;

    /**
     * 存储类型
     */
    private String storageClass;
    /**
     * 最后修改时间
     */
    private Long lastModified;

    private String bucketName;

    /**
     * 图片地址
     */
    @Transient
    private String imgUrl;

    /**
     * 图片样式
     */
    @Transient
    private String imgStyle;
}
