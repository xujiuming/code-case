package com.ming.timer.config.scheduler;

import com.ming.timer.controller.JobDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * boss 简易定时器
 * 用来快速管理 关于 boss 的定时任务
 *
 * @author ming
 * @date 2018-04-26 09:17
 */
@Component
@DependsOn({"schedulerInstance","jdbcTemplate"})
public class SimpleScheduler {
    private static final Logger logger = LogManager.getLogger(SimpleScheduler.class);

    private static final String JOB_GROUP = "mingJobGroup";
    private static final String TRIGGER_NAME = "mingJobTriggerName";
    private static final String TRIGGER_GROUP = "mingTriggerGroup";


    @Autowired
    private SchedulerInstance schedulerInstance;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加定时器
     *
     * @param jobName           任务名称
     * @param jobDesc           任务描述
     * @param triggerType       定时器触发类型
     * @param triggerExpression 定时器触发规则表达式
     */
    public void create(String jobName, String jobDesc, SchedulerInstance.TriggerType triggerType, String triggerExpression) {
        schedulerInstance.create(jobName, jobDesc, SimpleScheduler.JOB_GROUP, SimpleScheduler.TRIGGER_NAME, SimpleScheduler.TRIGGER_GROUP, triggerType, triggerExpression);
    }

    /**
     * 删除定时器
     *
     * @param jobName 定时器名称
     * @author ming
     * @date 2017-11-09 11:28
     */
    public void delete(String jobName) {
        schedulerInstance.delete(jobName, SimpleScheduler.JOB_GROUP);
    }

    /**
     * 修改定时器 执行触发器
     * 创建新的触发器  将已有的任务改变触发器
     *
     * @param jobName           任务名称
     * @param jobDesc           任务描述
     * @param triggerExpression 定时器触发规则表达式
     * @param triggerType       定时器触发规则类型
     * @author ming
     * @date 2017-11-09 11:37
     */
    public void update(String jobName, String jobDesc, SchedulerInstance.TriggerType triggerType, String triggerExpression) {
        schedulerInstance.update(jobName, jobDesc, SimpleScheduler.TRIGGER_NAME, SimpleScheduler.TRIGGER_GROUP, triggerType, triggerExpression);
    }


    /**
     * 暂停定时器
     *
     * @param jobName 任务名称
     * @author ming
     * @date 2017-11-09 11:38
     */
    public void pause(String jobName) {
        schedulerInstance.pause(jobName, SimpleScheduler.JOB_GROUP, SimpleScheduler.TRIGGER_NAME, SimpleScheduler.TRIGGER_GROUP);
    }

    /**
     * 重启暂停的定时器
     *
     * @param jobName 任务名称
     * @author ming
     * @date 2017-11-09 11:38
     */
    public void resume(String jobName) {
        schedulerInstance.resume(jobName, SimpleScheduler.JOB_GROUP, SimpleScheduler.TRIGGER_NAME, SimpleScheduler.TRIGGER_GROUP);
    }

    /**
     * 立即执行定时器
     *
     * @param jobName 任务名称
     * @author ming
     * @date 2017-11-09 11:40
     */
    public void run(String jobName) {
        schedulerInstance.run(jobName, SimpleScheduler.JOB_GROUP);
    }

    /**
     * 获取List<JobDto>
     *
     * @author ming
     * @date 2018-07-24 16:11:12
     */
    public List<Map<String,Object>> page(Integer number, Integer size) {

        return  jdbcTemplate.queryForList("select job_name as jobName ,description as jobDesc, trigger_type as triggerType ,  cron_expression as triggerExpression from qrtz_triggers qt  " +
                "left join qrtz_cron_triggers  qct on qt.sched_name = qct.sched_name  and qt.trigger_name = qct.trigger_name and qt.trigger_group = qct.trigger_group  " +
                "left join qrtz_simple_triggers qst on qt.sched_name = qst.sched_name and qt.trigger_name = qst.trigger_name and qt.trigger_group = qst.trigger_group  " +
                "limit 100 offset 0;");

    }
}
