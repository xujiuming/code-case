package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储已经触发的trigger相关信息
 *
 * @author ming
 * @date 2017-11-09 14:49
 */
@Entity
@Table(name = "QRTZ_FIRED_TRIGGERS")
@Data
public class QrtzFiredTriggers {
    @Id
    private String schedName;
    private String entryId;
    private String triggerName;
    private String triggerGroup;
    private String instanceName;
    private long firedTime;
    private long schedTime;
    private long priority;
    private String state;
    private String jobName;
    private String jobGroup;
    private String isNonconcurrent;
    private String requestsRecovery;

}
