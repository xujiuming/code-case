package com.ming.common.repository.log;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.common.entity.log.AccessLog;
import org.springframework.stereotype.Repository;

/**
 * @author ming
 * @date 2017-11-10 17:36
 */
@Repository
public interface AccessLogRepository extends BaseRepository<AccessLog, Long> {

}
