package com.ming.core.utils;

import java.util.regex.Pattern;

/**
 * 正则检验工具类
 *
 * @author ming
 * @date 2017-06-24
 */
public class RegexUtils {

    /**
     * ip的正则表达式
     */
    private static final String IP_REG = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";


    /**
     * 是否符合ip的规范
     *
     * @author ming
     * @date 2017-06-24
     */
    public static boolean isIP(String ip) {
        return Pattern.matches(IP_REG, ip);
    }
}
