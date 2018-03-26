package com.ming.core.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author ming
 * @date 2017-11-10 17:36
 */
@Slf4j
public class CacheUtils {


    /**
     * token cache
     * userId  token
     */
    public static Cache<Long, String> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .recordStats()
            .build();


    /**
     * guava cache 普通方式
     *
     * @author ming
     * @date 2017-08-28 10点
     */
    public static Cache<String, Object> paramCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .recordStats()
            .build();//定义成每次从数据库拿参数数据


    /**
     * 回调方式 使用cache  多种自定义类型cache
     *
     * @author ming
     * @date 2017-08-28 10点
     */
    private static Cache<String, Object> callableCache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .recordStats()
            .build();

    /**
     * 获取callable缓存  需要callable
     *
     * @author ming
     * @date 2017-08-28 16点
     */
    public static void getCallableCache(String key, Callable<Object> callable) {
        try {
            callableCache.get(key, callable);
        } catch (ExecutionException e) {
            log.error("callable方式获取缓存操作失败;参数:" + key);
            e.printStackTrace();
        }
    }


    /**
     * 获取缓存统计信息
     *
     * @author ming
     * @date 2017-08-28 16点
     */
    public static <K, V> CacheStats getStat(Cache<K, V> cache) {
        return cache.stats();
    }
}
