package com.ming.scheduler.entity.quartz;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储和定时容器相关的信息 和别的定时容器 在同一集群
 *
 * @author ming
 * @date 2017-11-09 14:50
 */
@Entity
@Table(name = "QRTZ_SCHEDULER_STATE")
@Data
public class QrtzSchedulerState {
    @Id
    private String schedName;
    private String instanceName;
    private long lastCheckinTime;
    private long checkinInterval;
}
