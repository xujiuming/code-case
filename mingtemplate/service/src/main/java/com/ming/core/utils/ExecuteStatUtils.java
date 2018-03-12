package com.ming.core.utils;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/**
 * 执行统计工具
 *
 * @author com.ming
 * @date 2017-12-13 13:07
 */
@Log4j2
public class ExecuteStatUtils {

    /**
     * 增加耗时
     *
     * @author com.ming
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
         * @author com.ming
         * @date 2017-12-13 13:07
         */
        void excute();
    }
}
