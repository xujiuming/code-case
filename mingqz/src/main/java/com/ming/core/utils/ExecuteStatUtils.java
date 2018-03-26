package com.ming.core.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 执行统计工具
 *
 * @author ming
 * @date 2017-12-13 13:07
 */
@Slf4j
public class ExecuteStatUtils {

    /**
     * 增加耗时
     *
     * @author ming
     * @date 2017-12-13 11:11
     */
    public static long excuteTimeStat(Task task, String msg) {
        long now = System.currentTimeMillis();
        task.excute();
        long end = System.currentTimeMillis();
        log.info("初始化" + msg + ",耗时:" + (end - now) + "ms");
        return end - now;
    }

    @FunctionalInterface
    public interface Task {

        /**
         * 定义函数方法 执行此lambda
         *
         * @author ming
         * @date 2017-12-13 13:07
         */
        void excute();
    }
}
