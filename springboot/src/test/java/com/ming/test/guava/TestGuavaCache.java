package com.ming.test.guava;

import com.google.common.cache.*;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

public class TestGuavaCache {

    /**
     * 缓存示例
     *
     * @author ming
     * @date 2017/8/7
     */
    @Test
    public void helloWorldTest() throws ExecutionException {
        LoadingCache<Long, String> strCache = CacheBuilder.newBuilder().maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, String>() {
                    //有这个键就从缓存中去 没有就根据load方法从新获取
                    //如果load没有显示抛出异常 可以用getUnchecked查找缓存 如果显示抛出 就不能使用getUnchecked
                    @Override
                    public String load(Long o) throws Exception {
                        return "缓存:" + o;
                    }

                    //批量加载
                    @Override
                    public Map<Long, String> loadAll(Iterable<? extends Long> keys) throws Exception {
                        Map<Long, String> tempMap = Maps.newConcurrentMap();
                        keys.forEach(key -> {
                            tempMap.put(key, "缓存:" + key);
                        });
                        return tempMap;
                    }

                    //重新加载
                    @Override
                    public ListenableFuture<String> reload(Long key, String oldValue) throws Exception {
                        return super.reload(key, oldValue);
                    }
                });
        System.out.println(strCache.get(1L));
        System.out.println(strCache.get(1L));
        System.out.println(strCache.get(2L));
    }


    /**
     * 回调方式 执行获取缓存 方便实现"如果有缓存则返回；否则运算、缓存、然后返回"
     * 可以在同一个cache对象中 通过不同方法获取 源数据
     *
     * @author ming
     * @date 2017/8/7
     */
    @Test
    public void callableTest() throws ExecutionException {
        //创建缓存对象 不重写cacheLoader 利用callable来从源数据获取缓存 不管有没有重写 callable优先
        Cache<Long, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<Long, String>() {
            //使用带回调方式获取缓存 优先执行回调方法获取的缓存
            @Override
            public String load(Long key) throws Exception {
                return "缓存:" + key;
            }
        });

        //创建 通过回调获取缓存
        System.out.println(cache.get(1L, new Callable<String>() {
            public String call() throws Exception {
                return "回调缓存:" + 1L;
            }
        }));

        System.out.println(cache.get(2L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "回调缓存2：" + 2;
            }
        }));
    }

    /**
     * 清除缓存监听器
     *
     * @author ming
     * @date 2017/8/8
     */
    @Test
    public void listenerTest() throws ExecutionException {
        RemovalListener<Long, String> removalListener = new RemovalListener<Long, String>() {
            //移除动作监听器  同步进行
            @Override
            public void onRemoval(RemovalNotification<Long, String> notification) {
                System.out.println("\n删除缓存:" + notification);
                System.out.println(notification.getKey());
                System.out.println(notification.getValue());
                //清除原因 返回是什么情况下清除的 例如超过大小、手动清除等
                System.out.println(notification.getCause());
                //是否是自动清除
                System.out.println(notification.wasEvicted());
            }
        };
        //装饰成异步的
        //RemovalListeners.asynchronous(removalListener, new Executor{...});
        Cache<Long, String> cache = CacheBuilder.newBuilder().maximumSize(1000)
                .removalListener(removalListener).build();
        //添加缓存
        cache.get(1L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "回调缓存:" + 1L;
            }
        });
        //显示删除缓存  被removalListener监听到
        cache.invalidate(1L);
    }


    /**
     * 刷新缓存
     *
     * @author ming
     * @date 2017/8/8
     */
    @Test
    public void refreshTest() throws ExecutionException, InterruptedException {
        //定时执行服务
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(10);
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //定时刷新 到时间后 访问过期数据后进行刷新 优先级比expireAfterWrite高
                //.refreshAfterWrite(100,TimeUnit.MILLISECONDS)
                //定时刷新 到时间直接刷新
                //.expireAfterWrite(100,TimeUnit.MILLISECONDS)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        return "load缓存+" + key;
                    }

                    @Override
                    public ListenableFuture<String> reload(Integer key, String oldValue) throws Exception {
                        //当key <2的时候 直接刷新 当key>=2 异步刷新
                        if (key < 2) {
                            return Futures.immediateFuture(oldValue);
                        } else {
                            //异步
                            ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
                                @Override
                                public String call() throws Exception {
                                    return "异步刷新缓存" + System.currentTimeMillis();
                                }
                            });
                            executor.execute(task);
                            return task;
                        }
                    }
                });

        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        //key<2
        cache.refresh(1);
        System.out.println(cache.get(1));
        //key >= 2 异步刷新
        cache.refresh(3);
        //由于是异步刷新 获取最新数据 主线程休眠1s
        Thread.sleep(1000);
        System.out.println(cache.get(3));
    }

    /**
     * 缓存统计信息
     *
     * @author ming
     * @date 2017/8/8
     */
    @Test
    public void statTest() throws ExecutionException {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //开启缓存统计功能
                .recordStats()
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        return "缓存:" + key;
                    }
                });
        //查询缓存
        for (int i = 0; i < 99; i++) {
            cache.get(i);
        }
        //查询已经缓存的数据 此时命中率 1%
        cache.get(1);
        CacheStats stats = cache.stats();
        //请求次数
        System.out.println("请求中次数:" + stats.requestCount());
        //命中次数
        System.out.println("命中次数:" + stats.hitCount());
        //命中率
        System.out.println("命中率:" + stats.hitRate());
        //miss数量
        System.out.println("miss数量:" + stats.missCount());
        //miss 比例
        System.out.println("miss率:" + stats.missRate());
        //加载数量
        System.out.println("加载总数量:" + stats.loadCount());
        //加载成功数量
        System.out.println("加载成功数量:" + stats.loadSuccessCount());
        //加载异常数量
        System.out.println("加载异常数量:" + stats.loadExceptionCount());
        //加载异常比例
        System.out.println("加载异常比例" + stats.loadExceptionRate());
        //加载总耗时 ns
        System.out.println("加载总耗时:" + stats.totalLoadTime());
        //加载新值的平均 时间  ns   (ns/1000 = ms)
        System.out.println("加载源数据平均时间:" + stats.averageLoadPenalty());
        //缓存被回收的总数量 显示清除不算
        System.out.println("被自动回收的数量:" + stats.evictionCount());
        // 减 本身-other 小于0  返回0
        //System.out.println(stats.minus(new CacheStats(...)));
        // 加 本身+other
        //System.out.println(stats.plus(new CacheStats(...)));
        System.out.println(stats);
    }
}
