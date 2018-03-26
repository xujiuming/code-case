package com.ming.test.spring.aop.advice;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class AdviceTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testBefore() {
        //创建 target
        Waiter waiter = new NaiveWaiter("1");
        //创建前置增强
        BeforeAdvice beforeAdvice = new com.ming.test.spring.aop.advice.BeforeAdvice();
        //创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        //开启 objenesisCglib 代理  target 不需要 有午餐构造函数
        proxyFactory.setOptimize(true);
        // 设置代理类
        proxyFactory.setTarget(waiter);
        //设置增强  可以指定 位置 构建成增强链 第一个是0
        proxyFactory.addAdvice(beforeAdvice);
        //获取代理对象
        Waiter proxy = (Waiter) proxyFactory.getProxy();
        //代理执行
        proxy.greetTo("ming");
        proxy.serveTo("ming");
    }
}
