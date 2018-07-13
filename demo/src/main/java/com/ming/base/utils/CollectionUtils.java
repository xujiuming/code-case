package com.ming.base.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {


    public static boolean notEmpty(Collection<?> collection) {
        return !org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }


    public static boolean notEmpty(Map<?, ?> map) {
        return !org.springframework.util.CollectionUtils.isEmpty(map);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return org.springframework.util.CollectionUtils.isEmpty(map);
    }


}
