package com.ming.common.service.impl.log;

import com.ming.base.BaseService;
import com.ming.common.entity.log.AccessLog;
import com.ming.common.repository.log.AccessLogRepository;
import com.ming.common.service.api.log.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志 服务
 *
 * @author ming
 * @date 2017-11-06 14:14
 */
@Service
public class AccessLogServiceImpl extends BaseService implements AccessLogService {
    @Autowired
    private AccessLogRepository accessLogRepository;

    /**
     * 保存 accessLog
     *
     * @author ming
     * @date 2017-11-06 14:16
     */
    @Override
    public AccessLog save(AccessLog accessLog) {
        return accessLogRepository.save(accessLog);
    }
}
