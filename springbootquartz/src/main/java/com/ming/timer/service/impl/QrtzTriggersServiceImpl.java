package com.ming.timer.service.impl;

import com.google.common.collect.Lists;
import com.ming.timer.config.scheduler.SchedulerInstance;
import com.ming.timer.controller.JobDTO;
import com.ming.timer.entity.QrtzTriggers;
import com.ming.timer.repository.QrtzTriggersRepository;
import com.ming.timer.service.QrtzCronTriggersService;
import com.ming.timer.service.QrtzSimpleTriggersService;
import com.ming.timer.service.QrtzTriggersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrtzTriggersServiceImpl implements QrtzTriggersService {
    @Autowired
    private QrtzTriggersRepository qrtzTriggersRepository;
    @Autowired
    private QrtzCronTriggersService qrtzCronTriggersService;
    @Autowired
    private QrtzSimpleTriggersService qrtzSimpleTriggersService;

    @Override
    @SuppressWarnings("unchecked")
    public Page<JobDTO> page(Pageable pageable) {
        Page<QrtzTriggers> qrtzTriggersPage = qrtzTriggersRepository.findAll(pageable);
        List<JobDTO> jobDTOList = Lists.newArrayList();
        qrtzTriggersPage.getContent().forEach(f -> {
            JobDTO tmp = new JobDTO();
            tmp.setJobName(f.getJobName());
            tmp.setJobDesc(f.getDescription());
            SchedulerInstance.TriggerType triggerType = SchedulerInstance.TriggerType.valueOf(f.getTriggerType());
            tmp.setTriggerType(triggerType);
            String triggerExpression = "";
            if (SchedulerInstance.TriggerType.CRON.equals(triggerType)) {
                triggerExpression = qrtzCronTriggersService.findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(f.getTriggerName(), f.getTriggerGroup(), f.getSchedName()).getCronExpression();
            } else if (SchedulerInstance.TriggerType.SIMPLE.equals(triggerType)) {
                triggerExpression = qrtzSimpleTriggersService.findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(f.getTriggerName(), f.getTriggerGroup(), f.getSchedName()).getRepeatInterval().toString();
            } else {
                throw new RuntimeException("没有这种类型的定时任务、数据异常");
            }
            tmp.setTriggerExpression(triggerExpression);
            jobDTOList.add(tmp);
        });
        Page<JobDTO> resultDto = new PageImpl(jobDTOList);

        BeanUtils.copyProperties(qrtzTriggersPage, resultDto);
        return resultDto;
    }
}
