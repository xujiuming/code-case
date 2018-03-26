package com.ming.test.spring.aop.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前置增强：：" + JSON.toJSONString(method) + "::" + JSON.toJSONString(args) + "::" + JSON.toJSONString(target));
    }
}
