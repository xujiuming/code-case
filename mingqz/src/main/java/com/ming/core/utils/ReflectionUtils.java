package com.ming.core.utils;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionUtils {


    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = Lists.newArrayList();
        addFields(clazz, fieldList);
        return fieldList;
    }

    /**
     * 获取当前class 对象 的所有 field  一直第归到object
     *
     * @author ming
     * @date 2017-12-14 17:35
     */
    private static void addFields(Class<?> clazz, List<Field> fieldList) {
        fieldList.addAll(Lists.newArrayList(clazz.getDeclaredFields()));
        if (!clazz.equals(Object.class)) {
            addFields(clazz.getSuperclass(), fieldList);
        }
    }
}
