package com.ming.timer.service.impl;

import com.ming.timer.entity.QrtzSimpleTriggers;
import com.ming.timer.repository.QrtzSimpleTriggersRepository;
import com.ming.timer.service.QrtzSimpleTriggersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrtzSimpleTriggersServiceImpl implements QrtzSimpleTriggersService {
    @Autowired
    private QrtzSimpleTriggersRepository qrtzSimpleTriggersRepository;
    @Override
    public QrtzSimpleTriggers findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(String triggerName, String triggerGroup, String schedName) {
        return qrtzSimpleTriggersRepository.findByTriggerNameIsAndTriggerGroupIsAndSchedNameIs(triggerName,triggerGroup,schedName);
    }
}
