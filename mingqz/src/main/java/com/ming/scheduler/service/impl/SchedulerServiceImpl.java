package com.ming.scheduler.service.impl;

import com.ming.base.BaseService;
import com.ming.base.orm.PageParam;
import com.ming.base.scheduler.SchedulerInstance;
import com.ming.core.utils.SpringBeanManager;
import com.ming.scheduler.controller.vo.JobVO;
import com.ming.scheduler.entity.ScheduleJobConfig;
import com.ming.scheduler.service.api.ScheduleJobConfigService;
import com.ming.scheduler.service.api.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author ming
 * @date 2017-11-10 10:36
 */
@Service
public class SchedulerServiceImpl extends BaseService implements SchedulerService {
    @Autowired
    private SchedulerInstance schedulerInstance;
    @Autowired
    private ScheduleJobConfigService scheduleJobConfigService;


    /**
     * 检测是否存在 jobBean
     *
     * @param jobName job名称
     * @author ming
     * @date 2017-11-13 11:47
     */
    private void checkJobBean(String jobName) {
        Object obj = SpringBeanManager.getBean(jobName);
        if (obj == null) {
            throw new RuntimeException("job bean  is null ！！");
        }
    }


    @Override
    public void create(JobVO jobVO) {
        {
            //校验常规参数

        }

        checkJobBean(jobVO.getJobName());
        ScheduleJobConfig scheduleJobConfig = new ScheduleJobConfig();
        scheduleJobConfig.setJobName(jobVO.getJobName());
        scheduleJobConfig.setJobType(jobVO.getJobType());
        scheduleJobConfig.setJobDesc(jobVO.getJobDesc());
        scheduleJobConfig.setJobGroup(ScheduleJobConfig.DEFAULT_GROUP);
        scheduleJobConfig.setTriggerGroup(ScheduleJobConfig.DEFAULT_GROUP);
        scheduleJobConfig.setTriggerName(jobVO.getTriggerName());
        scheduleJobConfig.setTriggerType(jobVO.getTriggerType());
        scheduleJobConfig.setTriggerExpression(jobVO.getTriggerExpression());
        scheduleJobConfig.setStatus(ScheduleJobConfig.STATUS_RUNNING);
        scheduleJobConfig.setCreateTimeMillis(System.currentTimeMillis());
        //scheduleJobConfigService.save(scheduleJobConfig);
        schedulerInstance.create(jobVO.getJobName()
                , jobVO.getJobDesc()
                , jobVO.getJobGroup()
                , jobVO.getTriggerName()
                , jobVO.getTriggerGroup()
                , SchedulerInstance.TriggerType.valueOf(jobVO.getTriggerType())
                , jobVO.getTriggerExpression());
    }

    @Override
    public void delete(String jobName, String jobGroup) {
        schedulerInstance.delete(jobName, jobGroup);
    }

    @Override
    public void update(JobVO jobVO) {
        checkJobBean(jobVO.getJobName());
        schedulerInstance.update(jobVO.getJobName()
                , jobVO.getJobDesc()
                , jobVO.getTriggerName()
                , jobVO.getTriggerGroup()
                , SchedulerInstance.TriggerType.valueOf(jobVO.getTriggerType())
                , jobVO.getTriggerExpression());
    }

    @Override
    public void pause(JobVO jobVO) {
        schedulerInstance.pause(jobVO.getJobName()
                , jobVO.getJobGroup()
                , jobVO.getTriggerName()
                , jobVO.getTriggerGroup());
    }

    @Override
    public void resume(JobVO jobVO) {
        schedulerInstance.resume(jobVO.getJobName(), jobVO.getJobGroup(), jobVO.getTriggerName(), jobVO.getTriggerGroup());
    }

    @Override
    public void run(String jobName, String jobGroup) {
        schedulerInstance.run(jobName, jobGroup);
    }


    @Override
    public Page<JobVO> page(PageParam pageParam) {

        //Page<ScheduleJobConfig> configs = scheduleJobConfigService.findPage(new PageRequest(1, 1));

        return null;
    }

    @Override
    public JobVO detail(String jobName) {
        return null;
    }
}
