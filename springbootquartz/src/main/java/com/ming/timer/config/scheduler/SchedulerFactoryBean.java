package com.ming.timer.config.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 暂时不自定义
 *
 * @author ming
 * @date 2017-11-09 18:09
 */
public class SchedulerFactoryBean extends org.springframework.scheduling.quartz.SchedulerFactoryBean {

    @Override
    protected void startScheduler(final Scheduler scheduler, final int startupDelay) throws SchedulerException {
        // 当时间小于0时，手动进行启动。
        if (startupDelay < 0) {
            logger.info("Does not automatically start!");
        } else {
            super.startScheduler(scheduler, startupDelay);
        }
    }

    /**
     * 处理jar包启动外部配置文件问题
     *
     * @author ming
     * @date 2018-04-26 14:20
     */
    @Override
    public void setConfigLocation(Resource configLocation) {
        super.setConfigLocation(new ClassPathResource("quartz.properties"));
    }

}
