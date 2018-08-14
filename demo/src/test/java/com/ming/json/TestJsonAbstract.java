package com.ming.json;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestJsonAbstract extends TestCase implements TestJson {
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
    //获取集合的大小
    protected int size = 10000;
    protected Long now = null;
    private Logger logger = LoggerFactory.getLogger(TestJsonAbstract.class);

    @Before
    public void init() {
        System.out.println("开始计算耗时。。。。。。");
        now = System.currentTimeMillis();
    }

    @After
    public void close() {
        System.out.println("结束耗时,共耗时:" + (System.currentTimeMillis() - now));
        logger.info("结束耗时,共耗时:" + (System.currentTimeMillis() - now));
        now = null;
    }


}
