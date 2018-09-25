package com.ming.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * jackson 单例对象  双检锁模式
 *
 * @author ming
 * @date 2018-08-23 09:56:58
 */
public class JacksonSingleton {
    private static volatile ObjectMapper objectMapper;

    private JacksonSingleton() {
    }


    public static ObjectMapper getInstance() {
        if (null == objectMapper) {
            synchronized (ObjectMapper.class) {
                if (null == objectMapper) {
                    objectMapper = new ObjectMapper();
                    //指定格式化时间
                    objectMapper.setDateFormat(new SimpleDateFormat(Constant.DATE_PATTERN));
                    //关闭 未知属性的校验
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
            }
        }
        return objectMapper;
    }
}
