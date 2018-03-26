package com.ming.job;

import com.alibaba.fastjson.JSON;
import com.ming.base.quartz.BaseSimpleJob;
import com.ming.core.utils.CollectionUtils;
import com.ming.core.utils.GroovyUtils;
import com.ming.core.utils.SpringBeanManager;
import com.ming.groovy.entity.GroovyJobConfig;
import com.ming.groovy.service.api.GroovyConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动注入 注册状态为已经注册 groovy脚本
 * 30s 执行一次   这个作为补偿定时器  保证 状态改为已经注册 可以进行自我补偿
 *
 * @author ming
 * @date 2017-12-12 11:25
 */
@Component
@Slf4j
public class AutoRegisterGroovyScriptJob extends BaseSimpleJob {
    @Autowired
    private GroovyConfigService groovyConfigService;

    @Override
    public void execute() {
        List<GroovyJobConfig> resultList = groovyConfigService.findRegisterListByNotBeanNameSet(SpringBeanManager.getManualRegisterBeanNameSet());
        //当所有bean 度注册
        if (CollectionUtils.isEmpty(resultList)) {
            return;
        }
        resultList.forEach(r -> {
            try {
                SpringBeanManager.registerBean(r.getBeanName(), GroovyUtils.newGroovyScriptJobObject(r.getScriptContent()).getClass());
                log.info("[注册groovy bean::]" + r.getBeanName());
            } catch (IllegalAccessException | InstantiationException e) {
                log.warn("【注册groovy bean 异常 】::" + JSON.toJSONString(r));
                e.printStackTrace();
            }
        });
    }
}
