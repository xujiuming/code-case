package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 吧trigger作为Bolb方式存储 创建自定义的触发器需要
 *
 * @author ming
 * @date 2017-11-09 14:48
 */
@Entity
@Table(name = "QRTZ_BLOB_TRIGGERS")
@Data
public class QrtzBlobTriggers {
    /**
     * 定时容器名称
     */
    @Id
    private String schedName;
    /**
     * 定时容器触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 二进制数据
     */
    private String blobData;

}
