package com.ming.common.service.impl.log;

import com.ming.base.BaseService;
import com.ming.common.entity.log.JobExecuteLog;
import com.ming.common.repository.log.JobExecuteLogRepository;
import com.ming.common.service.api.log.JobExecuteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定时任务执行日志 服务实现
 *
 * @author ming
 * @date 2017-11-13 17:56
 */
@Service
public class JobExecuteLogServiceImpl extends BaseService implements JobExecuteLogService {
    @Autowired
    private JobExecuteLogRepository jobExecuteLogRepository;


    /**
     * 保存 执行日志
     *
     * @param jobExecuteLog
     * @return JobExecuteLog
     * @author ming
     * @date 2017-11-13 17:57
     */
    @Override
    public JobExecuteLog save(JobExecuteLog jobExecuteLog) {
        return jobExecuteLogRepository.save(jobExecuteLog);
    }
}
