package com.ming.scheduler.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * job vo
 *
 * @author ming
 * @date 2017-11-10 10:42
 */
@Data
public class JobVO implements Serializable {
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务描述
     */
    private String jobDesc;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * 触发器类型
     * {@linkplain com.ming.base.scheduler.SchedulerInstance.TriggerType#CRON  cron 表达式}
     * {@linkplain com.ming.base.scheduler.SchedulerInstance.TriggerType#SIMPLE simple 秒数}
     */
    private String triggerType;
    /**
     * 触发器表达式
     */
    private String triggerExpression;
    /**
     * 触发器 名称
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    /**
     * 任务类型
     * {@linkplain com.ming.entity.scheduler.ScheduleJobConfig.JobType#type  任务类型}
     */
    private Integer jobType;


}
