package com.ming.service.log;

import com.ming.entity.log.AccessLog;
import com.ming.repository.log.AccessLogRepository;
import com.ming.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志 服务
 *
 * @author ming
 * @date 2017-11-06 14:14
 */
@Service
public class AccessLogService extends SupportService {
    @Autowired
    private AccessLogRepository accessLogRepository;

    /**
     * 保存 accessLog
     *
     * @author ming
     * @date 2017-11-06 14:16
     */
    public AccessLog save(AccessLog accessLog) {
        return accessLogRepository.save(accessLog);
    }
}
