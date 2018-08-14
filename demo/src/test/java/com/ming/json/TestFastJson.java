package com.ming.json;

import com.alibaba.fastjson.JSON;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Map;

/**
 * 测试fastJson 解析
 * 通过指定 test case 执行顺序 来保证 先调用obj转换String  然后在调用 string 转换obj
 *
 * @author ming
 * @date 2018-07-17 15:23:36
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TestFastJson extends TestJsonAbstract {


    @Test
    @Override
    public void testStringListObjToString() {
        stringList = JSON.toJSONString(DataUtils.getStringList(size));
        System.out.println(stringList);
    }


    @Test
    @Override
    public void testStringMapObjToString() {
        stringMap = JSON.toJSONString(DataUtils.getStringMap(size));
        System.out.println(stringMap);
    }

    @Test
    @Override
    public void testMyDataToString() {
        myData = JSON.toJSONString(DataUtils.getMyData());
        System.out.println(myData);
    }

    @Test
    @Override
    public void testMyDataListToString() {
        myDataList = JSON.toJSONString(DataUtils.getMyDataList(size));
        System.out.println(myDataList);
    }

    @Test
    @Override
    public void testMyDataMapToString() {
        myDataMap = JSON.toJSONString(DataUtils.getMyDataMap(size));
        System.out.println(myDataMap);
    }

    @Test
    @Override
    public void testCyclicDataToString() {
        cyclicData = JSON.toJSONString(DataUtils.getCyclicData());
        System.out.println(cyclicData);
    }

    @Test
    @Override
    public void testCyclicDataListToString() {
        cyclicDataList = JSON.toJSONString(DataUtils.getCyclicDataList(size));
        System.out.println(cyclicDataList);
    }

    @Test
    @Override
    public void testCyclicDataMapToString() {
        cyclicDataMap = JSON.toJSONString(DataUtils.getCyclicDataMap(size));
        System.out.println(cyclicDataMap);
    }

    @Test
    @Override
    public void testMyDataAndCyclicDataMapToString() {
        myDataAndCyclicDataMap = JSON.toJSONString(DataUtils.getMyDataAndCyclicDataMap(size));
        System.out.println(myDataAndCyclicDataMap);
    }


    @Test
    @Override
    public void zTestStringListObjStringToObj() {
        System.out.println(JSON.parseArray(stringList));
    }


    @Test
    @Override
    public void zTestStringMapObjStringToObj() {
        System.out.println(JSON.parseObject(stringMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataStringToObj() {
        System.out.println(JSON.parseObject(myData, MyData.class));
    }

    @Test
    @Override
    public void zTestMyDataListStringToObj() {
        System.out.println(JSON.parseArray(myDataList, MyData.class));
    }

    @Test
    @Override
    public void zTestMyDataMapStringToObj() {
        System.out.println(JSON.parseObject(myDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestCyclicDataStringToObj() {
        System.out.println(JSON.parseObject(cyclicData, CyclicData.class));
    }

    @Test
    @Override
    public void zTestCyclicDataListStringToObj() {
        System.out.println(JSON.parseArray(cyclicDataList, CyclicData.class));
    }

    @Test
    @Override
    public void zTestCyclicDataMapStringToObj() {
        System.out.println(JSON.parseObject(cyclicDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataAndCyclicDataMapStringToObj() {
        System.out.println(JSON.parseObject(myDataAndCyclicDataMap, Map.class));
    }


}
