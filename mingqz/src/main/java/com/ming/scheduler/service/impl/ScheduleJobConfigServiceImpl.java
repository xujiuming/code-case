package com.ming.scheduler.service.impl;

import com.ming.base.BaseService;
import com.ming.scheduler.entity.ScheduleJobConfig;
import com.ming.scheduler.repository.ScheduleJobConfigRepository;
import com.ming.scheduler.service.api.ScheduleJobConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 定时任务配置服务
 *
 * @author ming
 * @date 2017-11-13 10:37
 */
@Service
public class ScheduleJobConfigServiceImpl extends BaseService implements ScheduleJobConfigService {

    @Autowired
    private ScheduleJobConfigRepository scheduleJobConfigRepository;


    /**
     * 保存
     *
     * @param scheduleJobConfig
     * @return ScheduleJobConfig
     * @author ming
     * @date 2017-11-13 10:38
     */
    @Override
    public ScheduleJobConfig save(ScheduleJobConfig scheduleJobConfig) {
        // return scheduleJobConfigRepository.save(scheduleJobConfig);
        return null;
    }

    /**
     * 查询分页
     *
     * @param pageable
     * @return Page<ScheduleJobConfig>
     * @author ming
     * @date 2017-11-13 12:02
     */
    @Override
    public Page<ScheduleJobConfig> findPage(Pageable pageable) {
        //return scheduleJobConfigRepository.findAll(pageable);
        return null;
    }
}
