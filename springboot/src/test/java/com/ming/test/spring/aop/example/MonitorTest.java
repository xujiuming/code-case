package com.ming.test.spring.aop.example;

import com.ming.test.spring.aop.example.base.FunctionInterface;
import com.ming.test.spring.aop.example.base.TestFunction;
import com.ming.test.spring.aop.example.cglibProxy.MethodCglibProxy;
import com.ming.test.spring.aop.example.jdkProxy.MethodJdkProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class MonitorTest {

    @Test
    public void testMonitor() {
        TestFunction function = new TestFunction();
        function.testFunction();
    }


    /**
     * jdk 代理 是基于接口的
     *
     * @author ming
     * @date 2017-09-22 14:28
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testMonitorJdkProxy() {
        TestFunction function = new TestFunction();
        MethodJdkProxy handler = new MethodJdkProxy(function);
        //todo ming  jdk代理是基于接口的
        FunctionInterface proxyFunction = (FunctionInterface) Proxy.newProxyInstance(function.getClass().getClassLoader()
                , function.getClass().getInterfaces()
                , handler);
        proxyFunction.testFunctionProxy();
    }


    /**
     * cglib 代理
     *
     * @author ming
     * @date 2017-09-22 14:28
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testMonitorCglibProxy() {
        MethodCglibProxy proxy = new MethodCglibProxy();
        //todo ming cglib 可以织入类 直接代理 类  不能为final private代理
        TestFunction testFunction = (TestFunction) proxy.getProxy(TestFunction.class);
        testFunction.testFunctionProxy();
    }
}
