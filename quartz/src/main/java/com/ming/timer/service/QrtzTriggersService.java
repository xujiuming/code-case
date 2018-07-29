package com.ming.timer.service;

import com.ming.timer.controller.JobDTO;
import com.ming.timer.entity.QrtzTriggers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QrtzTriggersService {

    Page<JobDTO> page(Pageable pageable);

}
