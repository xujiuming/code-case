package com.ming.timer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class QrtzTriggers {

    private String schedName;
    private String triggerName;
    private String triggerGroup;
    @Id
    private String jobName;
    private String jobGroup;
    private String description;
    private Long nextFireTime;
    private Long prevFireTime;
    private Long priority;
    private String triggerState;
    private String triggerType;
    private Long startTime;
    private Long endTime;
    private String calendarName;
    private Long misfireInstr;
    private String jobData;


}
