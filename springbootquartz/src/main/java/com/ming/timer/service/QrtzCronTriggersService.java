package com.ming.timer.service;

import com.ming.timer.entity.QrtzCronTriggers;

public interface QrtzCronTriggersService {
    QrtzCronTriggers findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(String triggerName, String triggerGroup, String schedName);
}
