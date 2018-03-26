package com.ming.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 自定义线程池
 *
 * @author ming
 * @date 2017-11-13 17:36
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {
    public static final String SIMPLE_EXECUTOR_POOL = "simpleExecutorPool";


    /**
     * 简易线程池   用于处理简单 而且多线程运行效果明显的任务
     *
     * @author ming
     * @date 2017-12-13 10:22
     */
    @Bean(value = ThreadPoolConfig.SIMPLE_EXECUTOR_POOL)
    public Executor simpleExecutor() {
        long now = System.currentTimeMillis();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置为守护线程
        executor.setDaemon(true);
        //设置核心线程数量
        executor.setCorePoolSize(10);
        //设置最大线程数量
        executor.setMaxPoolSize(200);
        //设置队列 深度
        executor.setQueueCapacity(1000);
        //线程名称前缀
        executor.setThreadNamePrefix("simple-ExecutorPool");
        //初始化
        executor.initialize();
        log.info("初始化" + ThreadPoolConfig.SIMPLE_EXECUTOR_POOL + "线程池,耗时:" + (System.currentTimeMillis() - now) + "ms");
        return executor;
    }

}
