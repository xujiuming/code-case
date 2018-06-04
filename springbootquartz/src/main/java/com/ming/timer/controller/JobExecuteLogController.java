package com.ming.timer.controller;

import com.ming.timer.base.quartz.TimerControllerConstant;
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

  /*  @Autowired
    private QrtzJobExecuteLogMapper qrtzJobExecuteLogMapper;

    *//**
     * 分页查询 job 执行日志
     *
     * @author ming
     * @date 2018-04-26 13:26
     *//*
    @GetMapping("page")
    public PageInfo<QrtzJobExecuteLog> getJobLog(Page<QrtzJobExecuteLog> page) {
        Page<QrtzJobExecuteLog> result = PageHelper.startPage(page.getPageNum(), page.getPageSize())
                .doSelectPage(() -> qrtzJobExecuteLogMapper.findAll());
        return result.toPageInfo();
    }*/
}
