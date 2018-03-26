package com.ming.test.spring.aop.example.jdkProxy;

import com.ming.test.spring.aop.example.base.Monitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodJdkProxy implements InvocationHandler {

    private Object target;

    public MethodJdkProxy(Object target) {
        this.target = target;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Monitor.start(target.getClass().getName() + "." + method.getName());
        Object t = method.invoke(target, args);
        Monitor.end();
        return t;
    }


}
