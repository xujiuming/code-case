package com.ming.controller;

import com.google.common.collect.Lists;
import com.ming.entity.Log;
import com.ming.server.ILogController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("log")
public class LogController implements ILogController {

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
