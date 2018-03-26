package com.ming.common.service.api.log;

import com.ming.common.entity.log.JobExecuteLog;

/**
 * 定时任务 执行日志 服务
 *
 * @author ming
 * @date 2017-11-13 17:56
 */
public interface JobExecuteLogService {

    /**
     * 保存 执行日志
     *
     * @param jobExecuteLog
     * @return JobExecuteLog
     * @author ming
     * @date 2017-11-13 17:57
     */
    JobExecuteLog save(JobExecuteLog jobExecuteLog);
}
