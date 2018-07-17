package com.ming.json;

import org.junit.After;
import org.junit.Before;

public abstract class TestJsonAbstract implements TestJson {


    //公共变量区
    protected static String stringList = null;
    protected static String stringMap = null;
    protected static String myData = null;
    protected static String myDataList = null;
    protected static String myDataMap = null;
    protected static String cyclicData = null;
    protected static String cyclicDataList = null;
    protected static String cyclicDataMap = null;
    protected static String myDataAndCyclicDataMap = null;
    protected int size = 10000;
    protected Long now = null;

    @Before
    public void init() {
        System.out.println("开始计算耗时。。。。。。");
        now = System.currentTimeMillis();
    }

    @After
    public void close() {
        System.out.println("结束耗时,共耗时:" + (System.currentTimeMillis() - now));
        now = null;
    }


}
