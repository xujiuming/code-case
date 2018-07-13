package com.ming.timer.repository;

import com.ming.timer.base.orm.jpa.BaseRepository;
import com.ming.timer.entity.QrtzJobExecuteLog;
import org.springframework.stereotype.Repository;

@Repository
public interface QrtzJobExecuteLogRepository  extends BaseRepository<QrtzJobExecuteLog,Long> {

}
