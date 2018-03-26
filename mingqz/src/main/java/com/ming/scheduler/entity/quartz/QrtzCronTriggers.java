package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储 cron 触发器表达式 和时区信息
 *
 * @author ming
 * @date 2017-11-09 14:47
 */
@Entity
@Table(name = "QRTZ_CRON_TRIGGERS")
@Data
public class QrtzCronTriggers {
    /**
     * 定时容器名称
     */
    @Id
    private String schedName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器的组
     */
    private String triggerGroup;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 时区信息
     */
    private String timeZoneId;
}
