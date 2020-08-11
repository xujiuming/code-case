package com.ming.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * jackson 单例对象  双检锁模式
 *
 * @author only
 * @date 2018-08-23 09:56:58
 */
@Slf4j
public class JacksonJsonSingleton {
    private static volatile ObjectMapper objectMapper;

    private JacksonJsonSingleton() {
    }


    /**
     * 获取jackson 单例对象
     *
     * @return ObjectMapper
     * @author ming
     * @date 2018-10-09 11:22:21
     */
    public static ObjectMapper getInstance() {
        if (null == objectMapper) {
            synchronized (ObjectMapper.class) {
                if (null == objectMapper) {
                    //添加jackson 针对于jdk8的time包的序列化
                    JavaTimeModule javaTimeModule = new JavaTimeModule();
                    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(GlobalConstant.LOCAL_DATE_TIME_FORMAT_PATTERN)));
                    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(GlobalConstant.LOCAL_DATE_TIME_FORMAT_PATTERN)));

                    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(GlobalConstant.LOCAL_DATE_FORMAT_PATTERN)));

                    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(GlobalConstant.LOCAL_TIME_FORMAT_PATTERN)));
                    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(GlobalConstant.LOCAL_TIME_FORMAT_PATTERN)));
                    objectMapper = new ObjectMapper();
                    //添加javaTimeModule
                    objectMapper.registerModules(javaTimeModule);
                    //指定格式化时间
                    objectMapper.setDateFormat(new SimpleDateFormat(GlobalConstant.DATE_FORMAT_PATTERN));
                    //关闭 未知属性的校验
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    //null值不输出
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                }
            }
        }
        return objectMapper;
    }

    /**
     * 将json对象转化为json字符串
     *
     * @param o
     * @return String
     * @author ming
     * @date 2018-10-09 11:16:05
     */
    public static String writeString(Object o) {
        if (o == null) {
            return "{}";
        }
        try {
            return getInstance().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("json对象转化json字符串异常:{}", e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("无法将json对象转成json字符串");
    }

    /**
     * 将json字符串转化为指定的对象
     *
     * @param jsonStr
     * @param tClass
     * @return T
     * @author ming
     * @date 2018-10-09 11:20:02
     */
    public static <T> T readValue(String jsonStr, Class<T> tClass) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonStr), "json字符串不能为null");
        try {
            return getInstance().readValue(jsonStr, tClass);
        } catch (IOException e) {
            log.error("json字符串转化对象异常:{},{},{}", jsonStr, tClass, e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("无法将json字符串转化为对象");
    }

    /**
     * 将json字符串转化为对象  增加泛型类型选择
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return T
     * @author ming
     * @date 2018-10-17 20:48:37
     */
    @SuppressWarnings("unchecked")
    public static <T> T readValueByTypeReference(String jsonStr, TypeReference valueTypeRef) {
        try {
            return (T) getInstance().readValue(jsonStr, valueTypeRef);
        } catch (IOException e) {
            log.error("json字符串转化泛型对象异常:{},{},{}", jsonStr, valueTypeRef, e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("无法将json字符串转化为泛型对象");
    }

}
