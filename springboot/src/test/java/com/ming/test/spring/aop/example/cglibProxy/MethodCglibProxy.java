package com.ming.test.spring.aop.example.cglibProxy;

import com.ming.test.spring.aop.example.base.Monitor;
import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodCglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Monitor.start(obj.getClass().getName() + "." + method.getName());
        Object result = proxy.invokeSuper(obj, args);
        Monitor.end();
        return result;
    }
}
