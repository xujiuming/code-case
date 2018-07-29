package com.ming.timer.repository;

import com.ming.timer.base.orm.jpa.BaseRepository;
import com.ming.timer.entity.QrtzCronTriggers;
import org.springframework.stereotype.Repository;

@Repository
public interface QrtzCronTriggersRepository extends BaseRepository<QrtzCronTriggers,String> {

    QrtzCronTriggers findByTriggerNameIsAndTriggerGroupIsAndSchedNameIs(String triggerName, String triggerGroup, String schedName);
}
