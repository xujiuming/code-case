package com.ming.json;

public interface TestJson {

    //测试 对象转换 json 字符串 -------------------------------------------------------------------------------------------
    void testStringListObjToString();

    void testStringMapObjToString();

    void testMyDataToString();

    void testMyDataListToString();

    void testMyDataMapToString();

    void testCyclicDataToString();

    void testCyclicDataListToString();

    void testCyclicDataMapToString();

    void testMyDataAndCyclicDataMapToString();


    //json 字符串转换 对象测试方法------------------------------------------------------------------------------------------
    void zTestStringListObjStringToObj();

    void zTestStringMapObjStringToObj();

    void zTestMyDataStringToObj();

    void zTestMyDataListStringToObj();

    void zTestMyDataMapStringToObj();

    void zTestCyclicDataStringToObj();

    void zTestCyclicDataListStringToObj();

    void zTestCyclicDataMapStringToObj();

    void zTestMyDataAndCyclicDataMapStringToObj();
}
