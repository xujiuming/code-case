package com.ming.base;

import java.nio.charset.Charset;

/**
 * 全局的常量
 *
 * @author ming
 * @date 2018-09-27 14:23:07
 */
public class GlobalConstant {

    /**
     * 全局 日期 格式
     */
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LOCAL_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LOCAL_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String LOCAL_TIME_FORMAT_PATTERN = "HH:mm:ss";


    /**
     * 全局编码集
     */
    public static final Charset GLOBAL_CHARSET = Charset.forName("UTF-8");


    private GlobalConstant() {
    }


}
