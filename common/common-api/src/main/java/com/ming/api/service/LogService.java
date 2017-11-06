package com.ming.api.service;

import com.ming.entity.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * 日志服务
 *
 * @author ming
 * @date 2017-10-30 11:16
 */
@RequestMapping("log")
public interface LogService {
    /**
     * 根据id 获取日志 信息
     *
     * @param id
     * @author ming
     * @date 2017-10-30 11:30
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    Log findLogById(@RequestParam("id") Long id);

    /**
     * 根据 ids 获取日志信息集合
     *
     * @param ids
     * @author ming
     * @date 2017-10-30 11:30
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    List<Log> findLogListByIds(@RequestParam("ids") Collection<Long> ids);

}
