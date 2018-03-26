package com.ming.common.repository.log;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.common.entity.log.JobExecuteLog;
import org.springframework.stereotype.Repository;

/**
 * 定时任务执行日志 dao
 *
 * @author ming
 * @date 2017-11-13 17:56
 */
@Repository
public interface JobExecuteLogRepository extends BaseRepository<JobExecuteLog, Long> {
}
