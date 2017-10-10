package com.ming.server;

import com.ming.entity.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

public interface LogService {
    @RequestMapping("log/detail")
    Log findLogById(@RequestParam("id") Long id);
    @RequestMapping("log/list")
    List<Log> findLogListByIds(@RequestParam("ids") Collection<Long> ids);

}
