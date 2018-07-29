package com.ming.timer.controller;

/**
 * job 详情
 *
 * @author ming
 * @date 2018-04-26 11:01
 */
public class JobVO {
    private Long id;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;

    @Override
    public String toString() {
        return "JobVO{}";
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
