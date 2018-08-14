package com.ming.timer.service;

import com.ming.timer.entity.QrtzSimpleTriggers;

public interface QrtzSimpleTriggersService {
    QrtzSimpleTriggers findTriggerExpressionByTriggerNameAndTriggerGroupAndSchedName(String triggerName, String triggerGroup, String schedName);
}
