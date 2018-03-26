package com.ming.core.utils;

/**
 * 处理相关路径的工具类
 *
 * @author com.ming
 * @date 2017-06-18
 */
public class ResourcesUtils {

    /**
     * 系统根路径
     */
    @SuppressWarnings("unchecked")
    public static final String CLASS_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();


    /**
     * 获取某个类的路径
     *
     * @author com.ming
     * @date 2017-06-24
     */
    public static String getPath(Class clazz) {
        return clazz.getResource("").getPath();
    }


}
