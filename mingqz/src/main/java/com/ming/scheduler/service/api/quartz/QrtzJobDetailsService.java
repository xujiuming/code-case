package com.ming.scheduler.service.api.quartz;

import com.ming.scheduler.entity.quartz.QrtzJobDetails;

import java.util.List;

/**
 * @author ming
 * @date 2017-11-09 18:09
 */
public interface QrtzJobDetailsService {
    /**
     * 获取所有定时器详情
     *
     * @return List<QrtzJobDetails>
     * @author ming
     * @date 2017-11-09 18:08
     */
    List<QrtzJobDetails> findAll();
}
