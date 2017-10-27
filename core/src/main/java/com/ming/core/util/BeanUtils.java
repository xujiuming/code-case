package com.ming.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class BeanUtils implements ApplicationContextAware, DisposableBean {
    /**
     * spring bean上下文
     *
     * @author ming
     * @date 11:00
     */
    private static ApplicationContext applicationContext;

    /**
     * 根据名称 获取bean
     *
     * @param name 注册的bean名称
     * @return T
     * @author ming
     * @date 11:19
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBeanByName(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类型 获取bean
     *
     * @param clazz 注册bean的类型
     * @return T
     * @author ming
     * @date 11:20
     */
    public static <T> T getBeanByType(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }


    /**
     * 获取application中所有注册的bean 列表
     *
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanDefinitionNames() {
        checkApplicationContext();
        return applicationContext.getBeanDefinitionNames();
    }

    /**
     * 统计application中所有bean的数量
     *
     * @return Integer
     * @author ming
     * @date 2017-08-28 17点
     */
    public static Integer getBeanDefinitionCount() {
        checkApplicationContext();
        return applicationContext.getBeanDefinitionCount();
    }

    /**
     * 根据注解获取 beanNameList
     *
     * @param annotation
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanNameListByAnnotation(Class<? extends Annotation> annotation) {
        checkApplicationContext();
        return applicationContext.getBeanNamesForAnnotation(annotation);
    }

    /**
     * 根据bean类型获取所有的bean
     *
     * @param clazz
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanNamesForType(Class clazz) {
        checkApplicationContext();
        return applicationContext.getBeanNamesForType(clazz);
    }

    /**
     * 根据类型 获取 所有这个类型的bean  map 键为bean名字  值为注册的bean
     *
     * @param clazz
     * @return Map
     * @author ming
     * @date 2017-08-28 16点
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBeansOfType(clazz);
    }


    /**
     * 检测applicationcontext是否可用
     *
     * @author ming
     * @date 2017-08-28 17点
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("spring applicationContext is null !!!");
        }
    }


    /**
     * 销毁方法
     *
     * @author ming
     * @date 2017-08-28 17点
     */
    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    /**
     * 设置上下文
     *
     * @author ming
     * @date 11:17
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }
}
