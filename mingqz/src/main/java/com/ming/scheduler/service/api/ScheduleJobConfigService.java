package com.ming.scheduler.service.api;

import com.ming.scheduler.entity.ScheduleJobConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 定时器 定时任务服务
 *
 * @author ming
 * @date 2017-11-13 10:37
 */
public interface ScheduleJobConfigService {


    /**
     * 保存
     *
     * @param scheduleJobConfig
     * @return ScheduleJobConfig
     * @author ming
     * @date 2017-11-13 10:38
     */
    ScheduleJobConfig save(ScheduleJobConfig scheduleJobConfig);

    /**
     * 查询分页
     *
     * @param pageable
     * @return Page<ScheduleJobConfig>
     * @author ming
     * @date 2017-11-13 12:02
     */
    Page<ScheduleJobConfig> findPage(Pageable pageable);

}
