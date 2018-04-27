package com.ming.timer.mapper;


/**
 * 脚本自动生成 entity
 *
 * @author ming
 * @date 2018-04-26 13:37:54
 */
public class QrtzJobDetails {

    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private Boolean isDurable;
    private Boolean isNonconcurrent;
    private Boolean isUpdateData;
    private Boolean requestsRecovery;
    private String jobData;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
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

    public Boolean getIsDurable() {
        return isDurable;
    }

    public void setIsDurable(Boolean isDurable) {
        this.isDurable = isDurable;
    }

    public Boolean getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(Boolean isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    public Boolean getIsUpdateData() {
        return isUpdateData;
    }

    public void setIsUpdateData(Boolean isUpdateData) {
        this.isUpdateData = isUpdateData;
    }

    public Boolean getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(Boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    public String getJobData() {
        return jobData;
    }

    public void setJobData(String jobData) {
        this.jobData = jobData;
    }

}