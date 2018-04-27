package com.ming.timer.base;

import com.alibaba.fastjson.JSON;
import com.ming.timer.mapper.QrtzJobExecuteLog;
import com.ming.timer.mapper.QrtzJobExecuteLogMapper;
import com.ming.timer.utils.SpringBeanManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 代理执行 job  前后处理日志
 *
 * @author ming
 * @date 2017-11-09 16:11
 */
public class ProxyJob extends BaseJob {

    private static final Logger logger = LogManager.getLogger(ProxyJob.class);

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        BaseProxyJob job;
        QrtzJobExecuteLog jobExecuteLog = new QrtzJobExecuteLog();
        // job 名称
        jobExecuteLog.setJobName(jobName);
        // job 开始时间
        Long beginTime = System.currentTimeMillis();
        try {
            job = SpringBeanManager.getBean(jobName, BaseProxyJob.class);
            jobExecuteLog.setJobClass(job.getClass().getName());
            job.execute();
            //执行成功存储 这个任务详情
            jobExecuteLog.setContent(JSON.toJSONString(jobDetail));
        } catch (Exception e) {
            //执行异常 存储这个任务 异常信息
            jobExecuteLog.setContent(e.getMessage());
            logger.error("[执行异常]" + jobName + ":::" + e.getMessage());
            throw e;
        } finally {

            //任务执行耗费 时间
            jobExecuteLog.setExecuteTimeMillis(System.currentTimeMillis() - beginTime);
            System.out.println(JSON.toJSONString(jobExecuteLog));
            SpringBeanManager.getBean(QrtzJobExecuteLogMapper.class).insert(jobExecuteLog);
        }
    }
}
