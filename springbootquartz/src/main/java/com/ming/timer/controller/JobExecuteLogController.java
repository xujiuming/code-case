package com.ming.timer.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ming.timer.base.TimerControllerConstant;
import com.ming.timer.mapper.QrtzJobExecuteLog;
import com.ming.timer.mapper.QrtzJobExecuteLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * job执行日志 控制器
 *
 * @author ming
 * @date 2018-04-26 13:29
 */
@RestController
@RequestMapping(TimerControllerConstant.TIMER_BASE_URI + "log")
public class JobExecuteLogController {

    @Autowired
    private QrtzJobExecuteLogMapper qrtzJobExecuteLogMapper;

    /**
     * 分页查询 job 执行日志
     *
     * @author ming
     * @date 2018-04-26 13:26
     */
    @GetMapping("page")
    public Page<QrtzJobExecuteLog> getJobLog(Page<QrtzJobExecuteLog> page) {
        return PageHelper.startPage(page.getPageNum(), page.getPageSize())
                .doSelectPage(() -> qrtzJobExecuteLogMapper.findAll());
    }
}
