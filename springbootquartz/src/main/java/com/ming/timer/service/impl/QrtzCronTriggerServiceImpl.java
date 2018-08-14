package com.ming.timer.service.impl;

import com.ming.timer.entity.QrtzCronTriggers;
import com.ming.timer.repository.QrtzCronTriggersRepository;
import com.ming.timer.service.QrtzCronTriggersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrtzCronTriggerServiceImpl implements QrtzCronTriggersService {
    @Autowired
    private QrtzCronTriggersRepository qrtzCronTriggersRepository;
    @Override
    public QrtzCronTriggers findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(String triggerName, String triggerGroup, String schedName) {

        return qrtzCronTriggersRepository.findByTriggerNameIsAndTriggerGroupIsAndSchedNameIs(triggerName, triggerGroup, schedName);
    }
}
