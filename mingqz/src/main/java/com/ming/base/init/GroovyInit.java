package com.ming.base.init;

import com.alibaba.fastjson.JSON;
import com.ming.base.config.ThreadPoolConfig;
import com.ming.base.quartz.BaseProxyJob;
import com.ming.core.utils.CollectionUtils;
import com.ming.core.utils.GroovyUtils;
import com.ming.core.utils.SpringBeanManager;
import com.ming.groovy.entity.GroovyJobConfig;
import com.ming.groovy.service.api.GroovyConfigService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author ming
 * @date 2017-11-10 10:19
 */
@Slf4j
public class GroovyInit {


    /**
     * 初始化系统中 groovy bean  多线线程注册
     *
     * @author ming
     * @date 2017-12-13 09:45
     */
    public static void initGroovyBean() {
        // 查询所有有效的并且状态为注册状态的 groovy bean信息
        List<GroovyJobConfig> groovyJobConfigList = SpringBeanManager.getBean(GroovyConfigService.class).findRegisterList();
        if (CollectionUtils.isEmpty(groovyJobConfigList)) {
            return;
        }
        Executor executor = SpringBeanManager.getBean(ThreadPoolConfig.SIMPLE_EXECUTOR_POOL, Executor.class);
        // 调用基础线程池  执行
        groovyJobConfigList.forEach(g -> executor.execute(() -> {
            //初始化 scriptBean
            BaseProxyJob job = null;
            try {
                job = GroovyUtils.newGroovyScriptJobObject(g.getScriptContent());
                SpringBeanManager.registerBean(g.getBeanName(), job.getClass());
                GroovyUtils.GROOVY_SCRIPT_BEAN_MAP.put(g.getBeanName(), job.getClass());
                log.info("[初始化groovy bean::]" + g.getName());
            } catch (Exception e) {
                log.error("[初始化groovy bean失败]::任务信息:" + JSON.toJSONString(job) + "错误信息:" + e.getMessage());
                e.printStackTrace();
            }
        }));
    }


}
