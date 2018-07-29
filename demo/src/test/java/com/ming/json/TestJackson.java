package com.ming.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 测试解析 jackson
 *
 * @author ming
 * @date 2018-07-17 15:24:12
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TestJackson extends TestJsonAbstract {
    //获取 jackson 处理json的 mapper   可以通过setConfig 配置这个objectMapper
    private ObjectMapper objectMapper = new ObjectMapper();

    public void init1() {
        //DeserializationConfig,SerializationConfig
        //配置 objectMapper 编解码 配置
        //objectMapper.setConfig()

        //设置序列化和反序列化时候的配置
        //objectMapper.configure();

    }

    @Test
    @Override
    public void testStringListObjToString() throws JsonProcessingException {
        stringList = objectMapper.writeValueAsString(DataUtils.getStringList(size));
        System.out.println(stringList);
    }


    @Test
    @Override
    public void testStringMapObjToString() throws JsonProcessingException {
        stringMap = objectMapper.writeValueAsString(DataUtils.getStringMap(size));
        System.out.println(stringMap);
    }

    @Test
    @Override
    public void testMyDataToString() throws JsonProcessingException {
        myData = objectMapper.writeValueAsString(DataUtils.getMyData());
        System.out.println(myData);
    }

    @Test
    @Override
    public void testMyDataListToString() throws JsonProcessingException {
        myDataList = objectMapper.writeValueAsString(DataUtils.getMyDataList(size));
        System.out.println(myDataList);
    }

    @Test
    @Override
    public void testMyDataMapToString() throws JsonProcessingException {
        myDataMap = objectMapper.writeValueAsString(DataUtils.getMyDataMap(size));
        System.out.println(myDataMap);
    }

    @Test
    @Override
    public void testCyclicDataToString() throws JsonProcessingException {
        cyclicData = objectMapper.writeValueAsString(DataUtils.getCyclicData());
        System.out.println(cyclicData);
    }

    @Test
    @Override
    public void testCyclicDataListToString() throws JsonProcessingException {
        cyclicDataList = objectMapper.writeValueAsString(DataUtils.getCyclicDataList(size));
        System.out.println(cyclicDataList);
    }

    @Test
    @Override
    public void testCyclicDataMapToString() throws JsonProcessingException {
        cyclicDataMap = objectMapper.writeValueAsString(DataUtils.getCyclicDataMap(size));
        System.out.println(cyclicDataMap);
    }

    @Test
    @Override
    public void testMyDataAndCyclicDataMapToString() throws JsonProcessingException {
        myDataAndCyclicDataMap = objectMapper.writeValueAsString(DataUtils.getMyDataAndCyclicDataMap(size));
        System.out.println(myDataAndCyclicDataMap);
    }


    @Test
    @Override
    public void zTestStringListObjStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(stringList, List.class));
    }


    @Test
    @Override
    public void zTestStringMapObjStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(stringMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(myData, MyData.class));
    }

    @Test
    @Override
    public void zTestMyDataListStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(myDataList, List.class));
    }

    @Test
    @Override
    public void zTestMyDataMapStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(myDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestCyclicDataStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(cyclicData, CyclicData.class));
    }

    @Test
    @Override
    public void zTestCyclicDataListStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(cyclicDataList, List.class));
    }

    @Test
    @Override
    public void zTestCyclicDataMapStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(cyclicDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataAndCyclicDataMapStringToObj() throws IOException {
        System.out.println(objectMapper.readValue(myDataAndCyclicDataMap, Map.class));
    }

}
