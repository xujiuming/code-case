package com.ming.test.spring.aop.example.base;

public class TestFunction implements FunctionInterface {
    public void testFunction() {
        Monitor.start("com.ming.test.spring.aop.example.base.TestFunction");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Monitor.end();
    }


    public void testFunctionProxy() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
