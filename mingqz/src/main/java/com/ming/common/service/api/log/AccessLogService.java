package com.ming.common.service.api.log;

import com.ming.common.entity.log.AccessLog;

/**
 * @author ming
 * @date 2017-11-10 11:18
 */
public interface AccessLogService {
    /**
     * 保存 accessLog
     *
     * @param accessLog
     * @return AccessLog
     * @author ming
     * @date 2017-11-06 14:16
     */
    AccessLog save(AccessLog accessLog);
}
