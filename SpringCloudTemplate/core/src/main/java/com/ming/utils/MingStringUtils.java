package com.ming.utils;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * 字符串工具类
 *
 * @author ming
 * @date 2019-09-04 17:14:48
 */

public class MingStringUtils {
    /**
     * 将 byte 数组转换为 String 用utf8编码
     *
     * @param bytes 二进制数组
     * @return String 返回的字符串
     * @author ming
     * @date 2019-09-04 17:31:46
     */
    public static @NotNull
    String valueOfByUtf8(@NotNull byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }


    /**
     * 获取字符串中的大写字母 字符串
     *
     * @param str 参数字符串
     * @return String
     * @author ming
     * @date 2019-09-18 13:36:22
     */
    public static @NotNull
    String getUpperCaseChar(@NotNull String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}
