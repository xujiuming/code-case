package com.ming.timer.service.impl;

import com.ming.timer.entity.QrtzJobExecuteLog;
import com.ming.timer.repository.QrtzJobExecuteLogRepository;
import com.ming.timer.service.QrtzJobExecuteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QrtzJobExecuteLogServiceImpl implements QrtzJobExecuteLogService {
    @Autowired
    private QrtzJobExecuteLogRepository qrtzJobExecuteLogRepository;

    @Override
    public Page<QrtzJobExecuteLog> findPage(Pageable pageable) {
        return qrtzJobExecuteLogRepository.findAll(pageable);
    }

}
