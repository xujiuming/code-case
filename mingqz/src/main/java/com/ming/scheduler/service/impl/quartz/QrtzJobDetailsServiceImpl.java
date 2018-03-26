package com.ming.scheduler.service.impl.quartz;

import com.ming.base.BaseService;
import com.ming.scheduler.entity.quartz.QrtzJobDetails;
import com.ming.scheduler.repository.quartz.QrtzJobDetailsRepository;
import com.ming.scheduler.service.api.quartz.QrtzJobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ming
 * @date 2017-11-09 18:09
 */
@Service
public class QrtzJobDetailsServiceImpl extends BaseService implements QrtzJobDetailsService {

    @Autowired
    private QrtzJobDetailsRepository qrtzJobDetailsRepository;

    @Override
    public List<QrtzJobDetails> findAll() {
        return qrtzJobDetailsRepository.findAll();
    }
}
