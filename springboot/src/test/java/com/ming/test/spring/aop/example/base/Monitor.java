package com.ming.test.spring.aop.example.base;

import java.util.Objects;

public class Monitor {

    //创建一个本地线程监控
    private static ThreadLocal<MonitorInfo> threadLocal = new ThreadLocal<>();

    public static void start(String allMethodName) {
        System.out.println("----------------------------------------------");
        System.out.println(allMethodName + "monitor start....");
        MonitorInfo monitorInfo = new MonitorInfo(allMethodName);
        threadLocal.set(monitorInfo);
    }

    public static void end() {
        MonitorInfo info = threadLocal.get();
        info.endMonitor();
        System.out.println(info + "monitor end....");
        System.out.println("耗时:" + (info.getEnd() - info.getBegin()) + "ms");
        System.out.println("结果:" + Objects.toString(info));
        System.out.println("----------------------------------------------");
    }


}
