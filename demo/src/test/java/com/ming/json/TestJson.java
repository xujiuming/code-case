package com.ming.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface TestJson {

    //测试 对象转换 json 字符串 -------------------------------------------------------------------------------------------
    void testStringListObjToString() throws JsonProcessingException;

    void testStringMapObjToString() throws JsonProcessingException;

    void testMyDataToString() throws JsonProcessingException;

    void testMyDataListToString() throws JsonProcessingException;

    void testMyDataMapToString() throws JsonProcessingException;

    void testCyclicDataToString() throws JsonProcessingException;

    void testCyclicDataListToString() throws JsonProcessingException;

    void testCyclicDataMapToString() throws JsonProcessingException;

    void testMyDataAndCyclicDataMapToString() throws JsonProcessingException;


    //json 字符串转换 对象测试方法------------------------------------------------------------------------------------------
    void zTestStringListObjStringToObj() throws IOException;

    void zTestStringMapObjStringToObj() throws IOException;

    void zTestMyDataStringToObj() throws IOException;

    void zTestMyDataListStringToObj() throws IOException;

    void zTestMyDataMapStringToObj() throws IOException;

    void zTestCyclicDataStringToObj() throws IOException;

    void zTestCyclicDataListStringToObj() throws IOException;

    void zTestCyclicDataMapStringToObj() throws IOException;

    void zTestMyDataAndCyclicDataMapStringToObj() throws IOException;
}
