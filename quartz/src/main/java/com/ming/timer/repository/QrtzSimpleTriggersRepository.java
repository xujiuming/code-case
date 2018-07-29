package com.ming.timer.repository;

import com.ming.timer.base.orm.jpa.BaseRepository;
import com.ming.timer.entity.QrtzSimpleTriggers;
import org.springframework.stereotype.Repository;

@Repository
public interface QrtzSimpleTriggersRepository extends BaseRepository<QrtzSimpleTriggers,String> {
    QrtzSimpleTriggers findByTriggerNameIsAndTriggerGroupIsAndSchedNameIs(String triggerName, String triggerGroup, String schedName);

}
