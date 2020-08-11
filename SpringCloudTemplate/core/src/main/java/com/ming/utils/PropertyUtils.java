package com.ming.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;

import javax.validation.constraints.NotNull;

/**
 * 资源工具类
 *
 * @author ming
 * @date 2018-09-28 13:42:36
 */
public class PropertyUtils {

    private PropertyUtils() {
    }

    /**
     * 根据 key获取配置的值
     *
     * @param key
     * @return String value
     * @author ming
     * @date 2018-09-28 16:14:46
     */
    public static @NotNull
    String getProperty(@NotNull String key) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "key 不能为null ");
        String property = SpringBeanManagerUtils.getBean(Environment.class).getProperty(key);
        Preconditions.checkArgument(StringUtils.isNotEmpty(property), "无法获取" + key + "配置。。。。");
        return property;
    }

    /**
     * 获取当前运行的 配置环境
     *
     * @return String  active
     * @author ming
     * @date 2019-09-10 15:15:53
     */
    public static @NotNull
    String getActive() {
        return getProperty("spring.profiles.active");
    }
}
