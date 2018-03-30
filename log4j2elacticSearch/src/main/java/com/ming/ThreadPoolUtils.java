package com.ming;

import org.apache.lucene.util.NamedThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池 工具类
 *
 * @author ming
 * @date 2018-03-30 13:12
 */
public class ThreadPoolUtils {

    private static int coreSize = 800;
    private static int maxSize = 800;
    // 三小时 等待时间
    private static long keepAliveTime = 60 * 60 * 3L;
    private static TimeUnit timeUnit = TimeUnit.SECONDS;
    //todo  ming 使用同步队列  只是使用线程池   如果由于压力过大 直接架设 mq中间件
    private static BlockingQueue<Runnable> testQueue = new LinkedBlockingQueue<>(10000);
    private static ThreadFactory factory = new NamedThreadFactory("ming-");
    private static RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
        // todo ming  异常处理
        try {
            assert executor.getQueue() instanceof BlockingDeque;
            executor.getQueue().put(r);
        } catch (final InterruptedException e) {
            // a scaling queue never blocks so a put to it can never be interrupted
            //throw new AssertionError(e);
            throw new RuntimeException("mingxxxx");
        }

    };
    /**
     * 使用executors 创建线程池 只能测试使用 生产环境禁止使用
     *
     * @author ming
     * @date 2018-03-30 13:31
     */
    @Deprecated
    private static ExecutorService simpleExecutorService = Executors.newCachedThreadPool();


    /**
     * 定制的线程池
     *
     * @author ming
     * @date 2018-03-30 13:40
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, timeUnit, testQueue, factory, rejectedExecutionHandler);

    /**
     * 执行任务
     *
     * @author ming
     * @date 2018-03-30 13:48
     */

    public static void executeTask(Runnable runnable) {
        if (!executorService.isShutdown()) {
            executorService.execute(runnable);
        }
    }

    @Test
    public void e() {
        for (int i = 0; i < 1000000; i++) {
            int t = i;
            executorService.execute(() -> {
                System.out.println(1111 + "-----------------------" + t);
            });

        }
    }
}
