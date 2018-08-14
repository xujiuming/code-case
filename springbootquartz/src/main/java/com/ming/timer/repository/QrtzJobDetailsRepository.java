package com.ming.timer.repository;

import com.ming.timer.base.orm.jpa.BaseRepository;
import com.ming.timer.entity.QrtzJobDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface QrtzJobDetailsRepository extends BaseRepository<QrtzJobDetails,String> {
}
