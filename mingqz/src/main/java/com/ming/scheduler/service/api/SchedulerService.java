package com.ming.scheduler.service.api;

import com.ming.base.orm.PageParam;
import com.ming.scheduler.controller.vo.JobVO;
import org.springframework.data.domain.Page;

/**
 * 定时器服务接口
 *
 * @author ming
 * @date 2017-11-10 10:36
 */
public interface SchedulerService {

    /**
     * 添加定时器
     *
     * @param jobVO
     * @author ming
     * @date 2017-11-13 10:16
     */
    void create(JobVO jobVO);

    /**
     * 删除定时器
     *
     * @param jobName  定时器名称
     * @param jobGroup
     * @author ming
     * @date 2017-11-09 11:28
     */
    void delete(String jobName, String jobGroup);

    /**
     * 修改定时器
     *
     * @param jobVO
     * @author ming
     * @date 2017-11-09 11:37
     */
    void update(JobVO jobVO);


    /**
     * 暂停定时器
     *
     * @param jobVO
     * @author ming
     * @date 2017-11-09 11:38
     */
    void pause(JobVO jobVO);

    /**
     * 重启暂停的定时器
     *
     * @param jobVO
     * @author ming
     * @date 2017-11-09 11:38
     */
    void resume(JobVO jobVO);

    /**
     * 立即执行定时器
     *
     * @param jobName  任务名称
     * @param jobGroup
     * @author ming
     * @date 2017-11-09 11:40
     */
    void run(String jobName, String jobGroup);

    /**
     * 获取定时器分页参数
     *
     * @param pageParam
     * @return Page<JobVO>
     * @author ming
     * @date 2017-11-13 11:58
     */
    Page<JobVO> page(PageParam pageParam);

    /**
     * 定时器详情
     *
     * @param jobName
     * @return JobVO
     * @author ming
     * @date 2017-11-13 11:59
     */
    JobVO detail(String jobName);
}
