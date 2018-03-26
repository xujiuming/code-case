package com.ming.test.jdk8;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TestFuture {


    //建立最优数量的线程池
    /*
    * 线程池估算公式
    *Nthreads = Ncpu * Ucpu * (1 + W/C)
    * 其中：
    * ❑NCPU是处理器的核的数目，可以通过Runtime.getRuntime().availableProcessors()得到
    * ❑UCPU是期望的CPU利用率（该值应该介于0和1之间）
    * ❑W/C是等待时间与计算时间的比率
    * 电脑是4核的 希望使用cpu百分七十利用率  等待时间预估为比率为99
    * 4核 * 0.7 *(1+99) =  280线程
    *
    * */
    /**
     * 线程数量
     */
    private static final int threadNumber = (int) Math.ceil(Runtime.getRuntime().availableProcessors() * 0.7 * (1 + 99));

    /**
     * executor 旧版
     */
    @Deprecated
    private static final Executor executor1 = Executors.newFixedThreadPool(threadNumber, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            //设置为守护 线程
            t.setDaemon(true);
            return t;
        }
    });
    /**
     * executor 使用lambda方式写
     */
    private static final Executor executor = Executors.newFixedThreadPool(threadNumber, (r) -> {
        Thread t = new Thread(r);
        //设置为守护 线程
        t.setDaemon(true);
        return t;
    });

    /**
     * @author ming
     * @date 2017-08-24 18点
     */
    @Test
    public void jdk8之前的future() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Long> future = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
                //休眠2s  无法获取异步结果
                //Thread.sleep(2000);
                return System.currentTimeMillis();
            }
        });
        System.out.println("其他任务~~~~~~~~~~~~~~1111111111111111111111111");
        //获取结果 最多等待 1s
        Long result = null;
        try {
            result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //计算异常
            e.printStackTrace();
        } catch (ExecutionException e) {
            //等待中断
            e.printStackTrace();
        } catch (TimeoutException e) {
            //超时异常
            e.printStackTrace();
        }
        System.out.println("异步执行结果;" + result);
    }

    @Test
    public void 使用同步api() throws ExecutionException, InterruptedException {
        Shop shop = new Shop();
        String product = "ming";

        //测试同步api
        System.out.println("测试同步api");
        Long now = System.currentTimeMillis();
        System.out.println(shop.getPrice(product));
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("测试同步api结束");


    }

    //-----------------------批量查询最佳方案演进 begin-----------------------

    @Test
    public void 使用异步Lambda方式api() throws ExecutionException, InterruptedException {
        Shop shop = new Shop();
        String product = "ming";

        //测试异步api
        Long now = System.currentTimeMillis();
        System.out.println("测试异步api");
        now = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync(product);
        //先返回 future对象
        System.out.println("返回future对象" + future + "耗时:" + (System.currentTimeMillis() - now));
        System.out.println(future.get());
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("测试异步api 结束");
    }

    @Test
    public void 使用异步supplyAsyncAPI() throws ExecutionException, InterruptedException {
        Shop shop = new Shop();
        String product = "ming";

        //测试异步api
        Long now = System.currentTimeMillis();
        System.out.println("测试异步api");
        now = System.currentTimeMillis();
        Future<Double> future = shop.getPriceSupplyAsync(product);
        //先返回 future对象
        System.out.println("返回future对象" + future + "耗时:" + (System.currentTimeMillis() - now));
        System.out.println(future.get());
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("测试异步api 结束");
    }

    @Test
    public void 批量查询测试() {
        List<String> productList = Lists.newArrayList("xu", "ming", "sss", "sdfas", "sfdasfallllll", "jksallo");
        Long now = System.nanoTime();
        // stream 方式 同步查询
        System.out.println("----stream方式");
        System.out.println(findPricesByStream(productList));
        System.out.println("耗时:" + (System.nanoTime() - now));

        // parallelStream 方式  无法通过计算最佳线程方式提升
        now = System.nanoTime();
        System.out.println("----parallelStream方式");
        System.out.println(findPricesByParallelStream(productList));
        System.out.println("耗时:" + (System.nanoTime() - now));

        //completableFuture.supplyAsync 方式  可以通过使用最佳线程池 来提升
        now = System.nanoTime();
        System.out.println("----completableFuture.cupplyAsync方式");
        System.out.println(findPricesByCompletableFutureSupplyAsync(productList));
        System.out.println("耗时:" + (System.nanoTime() - now));

        //completableFuture.supplyAsync  最佳线程池方式
        now = System.nanoTime();
        System.out.println("----completableFuture.cupplyAsync 最佳线程池方式");
        System.out.println(findPriceByCompletableFutureSupplyAsyncAndExecutor(productList));
        System.out.println("耗时:" + (System.nanoTime() - now));


    }

    /**
     * 同步 查询 stream
     *
     * @author ming
     * @date 2017-08-24 22点
     */
    private List<String> findPricesByStream(List<String> productList) {
        Shop shop = new Shop();
        return productList.stream().map(product -> product + "::" + shop.getPrice(product)).collect(Collectors.toList());
    }

    /**
     * 通过parallelStream 来并行化处理
     *
     * @author ming
     * @date 2017-08-24 22点
     */
    private List<String> findPricesByParallelStream(List<String> productList) {
        Shop shop = new Shop();
        return productList.parallelStream().map(product -> product + "::" + shop.getPrice(product)).collect(Collectors.toList());
    }

    /**
     * 通过 completableFuture工厂方法 异步执行
     *
     * @author ming
     * @date 2017-08-24 22点
     */
    private List<String> findPricesByCompletableFutureSupplyAsync(List<String> productList) {
        Shop shop = new Shop();
        List<CompletableFuture<String>> comparableList = productList.stream()
                //当直接返回future 之后 继续下一个数据 不阻塞
                .map(product -> CompletableFuture.supplyAsync(() -> product + "::" + shop.getPrice(product)))
                .collect(Collectors.toList());
        return comparableList.stream()
                //join 阻塞 返回结果才会下一个
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 通过 completableFuture工厂方法 异步执行  制定最优线程池
     *
     * @author ming
     * @date 2017-08-24 23点
     */
    private List<String> findPriceByCompletableFutureSupplyAsyncAndExecutor(List<String> productList) {
        Shop shop = new Shop();
        List<CompletableFuture<String>> comparableList = productList.stream()
                //当直接返回future 之后 继续下一个数据 不阻塞  使用线程池
                .map(product -> CompletableFuture.supplyAsync(() -> product + "::" + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return comparableList.stream()
                //join 阻塞 返回结果才会下一个
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    //-----------------------批量查询最佳方案演进 end-----------------------

}

