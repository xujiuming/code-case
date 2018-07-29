package com.ming.timer.base.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;

/**
 * 基础job  抽象类
 *
 * @author ming
 * @PersistJobDataAfterExecution 表示 Quartz 将会在成功执行 execute() 方法后（没有抛出异常）更新 JobDetail 的 JobDataMap，下一次执行相同的任务（JobDetail）将会得到更新后的值，而不是原始的值。
 * @DisallowConcurrentExecution 禁止 并发执行 job
 * @date 2017-11-09 16:32
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class BaseJob extends QuartzJobBean implements Serializable {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        doExecute(context);
    }

    /**
     * 使用代理执行
     *
     * @param context
     * @throws JobExecutionException
     * @author ming
     * @date 2017-11-09 16:08
     */
    protected abstract void doExecute(JobExecutionContext context) throws JobExecutionException;
}
