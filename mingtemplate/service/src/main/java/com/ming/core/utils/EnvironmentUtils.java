package com.ming.core.utils;

import com.google.common.collect.Lists;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * 针对spring environment 的一些特殊地方 作处理
 *
 * @author ming
 * @date 2018-01-25 10:38
 */
public class EnvironmentUtils {

    /**
     * 获取yml配置数组的最大数量
     */
    private static final int DEFAULT_MAX_NUM = 100;

    /**
     * 获取 yml配置中的数组类型
     * 因为 environment 中获取yml的数组对象是 名称[n] 方式  所以采用这个方法获取
     *
     * @param env
     * @param str
     * @return List<String>
     * @author ming
     * @date 2018-01-25 10:40
     */
    public static List<String> getPropertyList(Environment env, String str) {
        List<String> result = Lists.newLinkedList();
        for (int i = 0; i < DEFAULT_MAX_NUM; i++) {
            String tmp = str + "[" + i + "]";
            if (!env.containsProperty(tmp)) {
                break;
            }
            result.add(env.getProperty(tmp));
        }
        return result;
    }

}
