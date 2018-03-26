package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 每个定时任务的详情
 *
 * @author ming
 * @date 2017-11-09 14:45
 */
@Entity
@Table(name = "QRTZ_JOB_DETAILS")
@Data
public class QrtzJobDetails {
    @Id
    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonconcurrent;
    private String isUpdateData;
    private String requestsRecovery;
    private String jobData;
}
