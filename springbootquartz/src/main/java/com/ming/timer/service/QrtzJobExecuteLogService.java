package com.ming.timer.service;

import com.ming.timer.entity.QrtzJobExecuteLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QrtzJobExecuteLogService {


    Page<QrtzJobExecuteLog> findPage(Pageable pageable);
}
