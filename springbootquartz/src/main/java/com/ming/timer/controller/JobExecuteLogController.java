package com.ming.timer.controller;

import com.ming.timer.base.quartz.TimerControllerConstant;
import com.ming.timer.base.web.BaseAbstractController;
import com.ming.timer.entity.QrtzJobExecuteLog;
import com.ming.timer.service.QrtzJobExecuteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class JobExecuteLogController extends BaseAbstractController {

    @Autowired
    private QrtzJobExecuteLogService qrtzJobExecuteLogService;

    /**
     * 分页查询 job 执行日志
     *
     * @author ming
     * @date 2018-04-26 13:26
     */
    @GetMapping("page")
    public Page<QrtzJobExecuteLog> getJobLog() {
        return qrtzJobExecuteLogService.findPage(buildPageable(1));
    }
}
