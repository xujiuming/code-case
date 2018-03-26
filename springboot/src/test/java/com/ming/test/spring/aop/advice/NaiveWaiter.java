package com.ming.test.spring.aop.advice;

public class NaiveWaiter implements Waiter {
    public NaiveWaiter(String name) {
    }

    @Override
    public void greetTo(String name) {
        System.out.println("欢迎::" + name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("滚蛋::" + name);
    }
}
