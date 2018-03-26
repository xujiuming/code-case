package com.ming.scheduler.repository.quartz;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.scheduler.entity.quartz.QrtzJobDetails;
import org.springframework.stereotype.Repository;

/**
 * @author ming
 * @date 2017-11-09 18:09
 */
@Repository
public interface QrtzJobDetailsRepository extends BaseRepository<QrtzJobDetails, String> {
}
