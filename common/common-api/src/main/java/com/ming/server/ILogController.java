package com.ming.server;

import com.ming.entity.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@RequestMapping("log")
public interface ILogController {
    @RequestMapping("detail")
    Log findLogById(@RequestParam("id") Long id);

    @RequestMapping("list")
    List<Log> findLogListByIds(@RequestParam("ids") Collection<Long> ids);

}
