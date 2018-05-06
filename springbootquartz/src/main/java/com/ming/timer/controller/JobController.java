package com.ming.timer.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.timer.base.TimerControllerConstant;
import com.ming.timer.config.scheduler.SimpleScheduler;
import com.ming.timer.mapper.QrtzJobDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * job 控制器
 *
 * @author ming
 * @date 2018-04-24 09:26
 */
@RestController
@RequestMapping(TimerControllerConstant.TIMER_BASE_URI + "job")
public class JobController {

    @Autowired
    private SimpleScheduler simpleScheduler;
    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;

    /**
     * 创建 job
     * 前提:
     * job内容必须要在 com.onlyedu.timer.job中实现
     *
     * @param jobDTO
     * @author ming
     * @date 2018-04-24 09:27
     */
    @PostMapping
    public Boolean createJob(JobDTO jobDTO) {
        simpleScheduler.create(jobDTO.getJobName(), jobDTO.getJobDesc(), jobDTO.getTriggerType(), jobDTO.getTriggerExpression());
        return Boolean.TRUE;
    }

    /**
     * 删除job
     *
     * @param jobName
     * @author ming
     * @date 2018-04-26 10:33
     */
    @DeleteMapping
    public Boolean deleteJob(String jobName) {
        simpleScheduler.delete(jobName);
        return Boolean.TRUE;
    }

    /**
     * 更新job
     *
     * @param jobDTO
     * @author ming
     * @date 2018-04-26 10:44
     */
    @PutMapping
    public Boolean updateJob(JobDTO jobDTO) {
        simpleScheduler.update(jobDTO.getJobName(), jobDTO.getJobDesc(), jobDTO.getTriggerType(), jobDTO.getTriggerExpression());
        return Boolean.TRUE;
    }

    /**
     * 查询任务详情
     *
     * @author ming
     * @date 2018-04-26 10:45
     */
    @GetMapping
    public JobVO getJobDetail() {
        return new JobVO();
    }

    /**
     * 获取job 分页
     *
     * @author ming
     * @date 2018-04-26 13:41
     */
    @GetMapping("page")
    public PageInfo<JobVO> getJobPage(Page<JobVO> page) {
        return PageHelper.startPage(page.getPageNum(),page.getPageSize()).doSelectPageInfo(()->qrtzJobDetailsMapper.findAll());
    }

    /**
     * 暂停定时任务
     *
     * @author ming
     * @date 2018-04-26 10:48
     */
    @PutMapping("pause")
    public Boolean pauseJob(String jobName) {
        simpleScheduler.pause(jobName);
        return Boolean.TRUE;
    }

    /**
     * 重启暂停的定时任务
     *
     * @author ming
     * @date 2018-04-26 10:53
     */
    @PutMapping("resume")
    public Boolean resumeJob(String jobName) {
        simpleScheduler.resume(jobName);
        return Boolean.TRUE;
    }

    /**
     * 立即运行 任务
     *
     * @author ming
     * @date 2018-04-26 10:56
     */
    @PutMapping("run")
    public Boolean runJob(String jobName) {
        simpleScheduler.run(jobName);
        return Boolean.TRUE;
    }

}
