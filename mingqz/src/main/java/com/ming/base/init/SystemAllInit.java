package com.ming.base.init;

import com.ming.core.utils.ExecuteStatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 系统初始化
 *
 * @author ming
 * @date 2017-11-09 17:32
 */
@Slf4j
// 等待springBeanManager 装载完毕  初始化本类
@DependsOn(value = "springBeanManager")
@Component
//尽量最后加载
@Order
public class SystemAllInit {

    @PostConstruct
    public void init() {
        //初始化 groovy bean
        ExecuteStatUtils.excuteTimeStat(GroovyInit::initGroovyBean, "初始化groovyBean");
        // 初始化 系统job
        ExecuteStatUtils.excuteTimeStat(SystemJobInit::initSystemJob, "初始化systemJob");
    }


    @PreDestroy
    public void preDestroy() {

    }
}
