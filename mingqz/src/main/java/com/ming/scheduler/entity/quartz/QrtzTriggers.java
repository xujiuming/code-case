package com.ming.scheduler.entity.quartz;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储每一个的触发器的信息
 *
 * @author ming
 * @date 2017-11-09 14:46
 */
@Entity
@Table(name = "QRTZ_TRIGGERS")
@Data
public class QrtzTriggers {
    @Id
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String description;
    private long nextFireTime;
    private long prevFireTime;
    private long priority;
    private String triggerState;
    private String triggerType;
    private long startTime;
    private long endTime;
    private String calendarName;
    private long misfireInstr;
    private String jobData;
}