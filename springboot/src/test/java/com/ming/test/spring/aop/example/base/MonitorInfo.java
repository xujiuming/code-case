package com.ming.test.spring.aop.example.base;

import lombok.Data;


@Data
public class MonitorInfo {
    private long begin;
    private long end;
    private String allMethodName;

    public MonitorInfo(String allMethodName) {
        this.allMethodName = allMethodName;
        this.begin = System.currentTimeMillis();
    }

    public void endMonitor() {
        this.end = System.currentTimeMillis();
    }
}
