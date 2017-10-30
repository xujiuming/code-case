package com.ming.core.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 *扩充基本集合工具类
 *@author ming
 *@date 2017-10-30 11:19
 */
public class CollectionUtil {

    public static <T>  boolean isEmpty(Collection<T> collection){
        return CollectionUtils.isEmpty(collection);
    }

    public static <T> boolean notEmpty(Collection<T> collection){
        return !CollectionUtils.isEmpty(collection);
    }

    public static <K,V> boolean isEmpty(Map<K,V> map){
        return CollectionUtils.isEmpty(map);
    }

    public static <K,V> boolean notEmpty(Map<K,V> map){
        return !CollectionUtils.isEmpty(map);
    }
}
