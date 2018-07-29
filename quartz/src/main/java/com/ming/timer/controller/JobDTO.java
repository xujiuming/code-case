package com.ming.timer.controller;


import com.ming.timer.config.scheduler.SchedulerInstance;

/**
 * job dto
 *
 * @author ming
 * @date 2018-04-26 10:30
 */
public class JobDTO {
    /**
     * job 名字
     */
    private String jobName;
    /**
     * job 描述
     */
    private String jobDesc;
    /**
     * 表达式类型
     */
    private SchedulerInstance.TriggerType triggerType;
    /**
     * 表达式格式
     */
    private String triggerExpression;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public SchedulerInstance.TriggerType getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(SchedulerInstance.TriggerType triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerExpression() {
        return triggerExpression;
    }

    public void setTriggerExpression(String triggerExpression) {

        this.triggerExpression = triggerExpression;
    }


    @Override
    public String toString() {
        return "JobDTO{" +
                "jobName='" + jobName + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                ", triggerType=" + triggerType +
                ", triggerExpression='" + triggerExpression + '\'' +
                '}';
    }
}
