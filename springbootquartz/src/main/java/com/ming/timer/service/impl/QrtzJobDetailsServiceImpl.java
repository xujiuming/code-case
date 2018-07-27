package com.ming.timer.service.impl;

import com.ming.timer.entity.QrtzJobDetails;
import com.ming.timer.repository.QrtzJobDetailsRepository;
import com.ming.timer.service.QrtzJobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QrtzJobDetailsServiceImpl implements QrtzJobDetailsService {
    @Autowired
    private QrtzJobDetailsRepository qrtzJobDetailsRepository;


    @Override
    public Page<QrtzJobDetails> findPage(Pageable pageable) {
        return qrtzJobDetailsRepository.findAll(pageable);
    }


}
