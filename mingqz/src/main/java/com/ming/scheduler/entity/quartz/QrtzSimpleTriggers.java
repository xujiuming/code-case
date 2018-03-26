package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储简单的触发器 重复次数 间隔  已经触发的次数
 *
 * @author ming
 * @date 2017-11-09 14:47
 */
@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS")
@Data
public class QrtzSimpleTriggers {
    @Id
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private long repeatCount;
    private long repeatInterval;
    private long timesTriggered;
}
