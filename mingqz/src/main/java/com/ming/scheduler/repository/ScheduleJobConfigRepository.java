package com.ming.scheduler.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.scheduler.entity.ScheduleJobConfig;
import org.springframework.stereotype.Repository;

/**
 * 定时容器 定时任务 配置 dao
 *
 * @author ming
 * @date 2017-11-13 10:31
 */
@Repository
public interface ScheduleJobConfigRepository extends BaseRepository<ScheduleJobConfig, Long> {
}
