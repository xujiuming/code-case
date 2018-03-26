package com.ming.base.quartz;

import com.alibaba.fastjson.JSON;
import com.ming.base.config.ThreadPoolConfig;
import com.ming.common.entity.log.JobExecuteLog;
import com.ming.common.service.api.log.JobExecuteLogService;
import com.ming.core.utils.SpringBeanManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.Executor;

/**
 * 代理执行 job  前后处理日志
 *
 * @author ming
 * @date 2017-11-09 16:11
 */
@Slf4j
public class ProxyJob extends BaseJob {

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        BaseProxyJob job;
        JobExecuteLog jobExecuteLog = new JobExecuteLog();
        // job 名称
        jobExecuteLog.setJobName(jobName);
        // job 开始时间
        jobExecuteLog.setJobStartTime(System.currentTimeMillis());
        try {
            job = SpringBeanManager.getBean(jobName, BaseProxyJob.class);
            job.execute();
            //执行成功存储 这个任务详情
            jobExecuteLog.setContent(JSON.toJSONString(jobDetail));
        } catch (Exception e) {
            //执行异常 存储这个任务 异常信息
            jobExecuteLog.setContent(e.getMessage());
            log.error("[执行异常]" + jobName + ":::" + e.getMessage());
        } finally {
            //任务执行结束 时间
            jobExecuteLog.setJobEndTime(System.currentTimeMillis());
            //日志创建时间
            jobExecuteLog.setCreateTimeMillis(System.currentTimeMillis());
            //获取 简易线程池 提交任务
            ((Executor) SpringBeanManager.getBean(ThreadPoolConfig.SIMPLE_EXECUTOR_POOL)).execute(() -> {
                SpringBeanManager.getBean(JobExecuteLogService.class).save(jobExecuteLog);
            });
        }
    }
}
