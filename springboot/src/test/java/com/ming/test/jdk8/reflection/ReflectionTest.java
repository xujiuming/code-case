package com.ming.test.jdk8.reflection;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * 反射的测试用例
 *
 * @author ming
 * @date 2017-09-09
 */
public class ReflectionTest {

    //获取类对象  类.class方式
    Class<String> clazz = String.class;

    /**
     * 获取类的信息
     * 反射开头 首先就要先获取class对象 才能去进一步的操作 类的相关信息
     * 例如 可以通过配置 去加载服务器中path中的class 然后动态注入到spring 容器中
     * 实现一写变态的需求 (动态加载定时任务class 可以在不重启项目的情况下修改调度任务 这个需求真的是很变态 无力吐槽)
     * {@linkplain Class 类对象}
     *
     * @author ming
     * @date 2017-09-09
     */
    @Test
    public void testClass() {
        //获取类名
        System.out.println("类的全限定名:" + clazz.getName());
        System.out.println("类名:" + clazz.getSimpleName());

        //获取类的修饰符  已知String 是public final修饰  Modifier类中包含java所有的修饰符的枚举
        int modifier = clazz.getModifiers();
        System.out.println("是否是final修饰:" + Modifier.isFinal(modifier));
        ;
        System.out.println("是否是public修饰:" + Modifier.isPublic(modifier));

        //获取包信息
        Package p = clazz.getPackage();
        System.out.println("String包信息:" + JSON.toJSONString(p));

        //获取父类信息
        System.out.println("父类信息:" + JSON.toJSONString(clazz.getSuperclass()));

        //实现的接口   只能获取 当前类的实现的  父类的要先获取父类class 然后再获取
        System.out.println("当前类实现的接口:" + JSON.toJSONString(clazz.getInterfaces()));

        //获取构造函数
        System.out.println("获取由访问权限的函数:" + JSON.toJSONString(clazz.getConstructors()));
        System.out.println("获取所有的构造函数:" + JSON.toJSONString(clazz.getDeclaredConstructors()));
        //获取所有的方法
        System.out.println("获取所有的方法:" + JSON.toJSONString(clazz.getMethods()));

        //获取所有的变量 私有的无法获取
        System.out.println("获取有访问权限的变量:" + JSON.toJSONString(clazz.getFields()));
        //获取所有的变量 包含私有的
        System.out.println("获取所有的变量:" + JSON.toJSONString(clazz.getDeclaredFields()));

        //获取注解  这个经常用 来写注解处理器的必用
        System.out.println("获取类的注解:" + JSON.toJSONString(clazz.getAnnotations()));
    }


    /**
     * 从class获取构造函数 可以通过构造函数去实例化对象
     * {@linkplain java.lang.reflect.Constructor 构造函数对象}
     *
     * @author ming
     * @date 2017-09-09
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取所有 构造函数
        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println("所有的构造函数:" + JSON.toJSONString(constructors));

        //获取构造函数中的参数类型
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            System.out.println("构造函数:" + JSON.toJSONString(constructor) + "::参数:" + JSON.toJSONString(parameterTypes));
        }

        //获取构造函数对象  new String(String str)
        Constructor constructor = clazz.getConstructor(String.class);
        String s = (String) constructor.newInstance("ming");
        System.out.println("通过反射构造函数实例化的对象:" + JSON.toJSONString(s));

    }


    /**
     * 反射获取类中字段信息
     *
     * @author ming
     * @date 2017-09-13 16:30
     */
    @Test
    public void testFields() throws IllegalAccessException {
        //获取属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("变量属性:" + JSON.toJSONString(field));
        }

        //任意获取一个 属性
        Field field = fields[0];
        field.get(1);
        field.set(clazz, 1);
    }


    /**
     * 反射获取 method 并且代理执行
     *
     * @author ming
     * @date 2017-09-13 16:30
     */
    @Test
    public void testMethod() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("方法属性:" + JSON.toJSONString(method));
        }

        Method method = methods[0];
        method.invoke(null, "111");
    }


}
