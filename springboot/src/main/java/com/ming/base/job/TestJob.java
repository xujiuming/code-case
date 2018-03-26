package com.ming.base.job;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("qz定时任务"+JSON.toJSONString(jobExecutionContext));
    }
}
