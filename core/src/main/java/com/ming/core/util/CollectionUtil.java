package com.ming.core.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

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
