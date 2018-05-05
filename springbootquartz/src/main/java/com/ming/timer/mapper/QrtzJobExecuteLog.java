package com.ming.timer.mapper;

import java.util.Date;


/**
 * 脚本自动生成 entity
 *
 * @author ming
 * @date 2018-04-26 13:12:11
 */
public class QrtzJobExecuteLog {

    private Integer id;
    private String jobName;
    private String jobClass;
    private String content;
    private Long executeTimeMillis;
    private Date gmtCreate;
    private Date gmtModified;
    private Boolean isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getExecuteTimeMillis() {
        return executeTimeMillis;
    }

    public void setExecuteTimeMillis(Long executeTimeMillis) {
        this.executeTimeMillis = executeTimeMillis;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}