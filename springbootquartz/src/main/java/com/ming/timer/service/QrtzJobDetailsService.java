package com.ming.timer.service;

import com.ming.timer.entity.QrtzJobDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QrtzJobDetailsService {
    Page<QrtzJobDetails> findPage(Pageable pageable);
}
