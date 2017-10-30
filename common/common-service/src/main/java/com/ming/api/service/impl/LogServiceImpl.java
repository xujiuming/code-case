package com.ming.api.service.impl;

import com.google.common.collect.Lists;
import com.ming.api.service.LogService;
import com.ming.entity.Log;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 *日志服务实现
 *@author ming
 *@date 2017-10-30 11:17
 */
@RestController
public class LogServiceImpl implements LogService {

    @Override
    public Log findLogById(@RequestParam("id") Long id) {
        return new Log(id, "log");
    }

    @Override
    public List<Log> findLogListByIds(@RequestParam("ids") Collection<Long> ids) {
        List<Log> result = Lists.newArrayList();
        for (Long id : ids) {
            result.add(new Log(id, "logs"));
        }
        return result;
    }
}
