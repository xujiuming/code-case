package com.ming.base.init;

import com.ming.core.utils.SpringBeanManager;
import com.ming.job.AutoRegisterGroovyScriptJob;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统定时任务初始化
 *
 * @author ming
 * @date 2017-12-13 09:12
 */
@Slf4j
public class SystemJobInit {


    /**
     * 初始化 系统job
     *
     * @author ming
     * @date 2017-12-13 09:41
     */
    public static void initSystemJob() {
        SpringBeanManager.getBean(AutoRegisterGroovyScriptJob.class).execute();
    }
}
