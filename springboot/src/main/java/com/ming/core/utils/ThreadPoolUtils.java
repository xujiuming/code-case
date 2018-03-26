package com.ming.core.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {


    /**
     * executor service
     * 线程数量是有SystemThreadNumber计算
     *
     * @author ming
     * @date 2017-08-31 13点
     */

    public static final ExecutorService executor = Executors.newFixedThreadPool(SystemThreadNumber.getThreadNumber()
            , (r) -> {
                Thread thread = new Thread(r);
                //设置为守护现成
                thread.setDaemon(true);
                return thread;
            });

    /**
     * 系统比较合适的线程数量
     *
     * @author ming
     * @date 2017-08-31 13点
     */
    static class SystemThreadNumber {

        /**
         * 当前机器 cpu数量
         */
        private static Integer Ncpu = Runtime.getRuntime().availableProcessors();

        /*
        * 默认利用率
        * */
        private static Double defaultUCpu = 0.5;

        /*
        * 等待时间和计算时间比率 暂定50
        * */
        private static Integer wc = 50;

        /**
         * 根据公式获取当前运行环境较为合适的线程数
         *
         * @author ming
         * @date 2017-08-28 15点
         */
        @SuppressWarnings("unchecked")
        public static int getThreadNumber() {
            return (int) Math.ceil(Ncpu * defaultUCpu * (1 + wc));
        }
    }
}
