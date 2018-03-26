package com.ming.repository.log;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.entity.log.AccessLog;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends BaseRepository<AccessLog, Long> {
}
