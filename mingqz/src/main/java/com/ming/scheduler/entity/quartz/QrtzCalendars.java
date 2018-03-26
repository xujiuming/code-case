package com.ming.scheduler.entity.quartz;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 以bolb方式存储 quartz的 calendar信息
 *
 * @author ming
 * @date 2017-11-09 14:48
 */
@Entity
@Table(name = "QRTZ_CALENDARS")
@Data
public class QrtzCalendars {
    /**
     * 定时容器名称
     */
    @Id
    private String schedName;
    /**
     * 日历名称
     */
    private String calendarName;
    /**
     * 日历的二进制数据
     */
    private String calendar;
}
