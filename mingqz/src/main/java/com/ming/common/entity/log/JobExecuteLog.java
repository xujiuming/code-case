package com.ming.common.entity.log;

import com.ming.base.orm.InId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 定时任务执行日志
 *
 * @author ming
 * @date 2017-11-13 17:48
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class JobExecuteLog extends InId {

    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务开始时间
     */
    private Long jobStartTime;
    /**
     * 任务结束时间
     */
    private Long jobEndTime;
    /**
     * 任务 附加信息 （异常信息 或者其他手动添加的信息）
     */
    private String content;

}
