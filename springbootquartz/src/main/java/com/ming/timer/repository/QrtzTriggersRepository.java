package com.ming.timer.repository;

import com.ming.timer.base.orm.jpa.BaseRepository;
import com.ming.timer.entity.QrtzTriggers;
import org.springframework.stereotype.Repository;

@Repository
public interface QrtzTriggersRepository extends BaseRepository<QrtzTriggers ,String> {
}
