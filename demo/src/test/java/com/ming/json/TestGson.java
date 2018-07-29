package com.ming.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

/**
 * 测试gson 解析
 *
 * @author ming
 * @date 2018-07-17 15:23:58
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGson extends TestJsonAbstract {

    private Gson gson = new GsonBuilder().create();


    @Test
    @Override
    public void testStringListObjToString() {
        stringList = gson.toJson(DataUtils.getStringList(size));
        System.out.println(stringList);
    }


    @Test
    @Override
    public void testStringMapObjToString() {
        stringMap = gson.toJson(DataUtils.getStringMap(size));
        System.out.println(stringMap);
    }

    @Test
    @Override
    public void testMyDataToString() {
        myData = gson.toJson(DataUtils.getMyData());
        System.out.println(myData);
    }

    @Test
    @Override
    public void testMyDataListToString() {
        myDataList = gson.toJson(DataUtils.getMyDataList(size));
        System.out.println(myDataList);
    }

    @Test
    @Override
    public void testMyDataMapToString() {
        myDataMap = gson.toJson(DataUtils.getMyDataMap(size));
        System.out.println(myDataMap);
    }

    @Test
    @Override
    public void testCyclicDataToString() {
        cyclicData = gson.toJson(DataUtils.getCyclicData());
        System.out.println(cyclicData);
    }

    @Test
    @Override
    public void testCyclicDataListToString() {
        cyclicDataList = gson.toJson(DataUtils.getCyclicDataList(size));
        System.out.println(cyclicDataList);
    }

    @Test
    @Override
    public void testCyclicDataMapToString() {
        cyclicDataMap = gson.toJson(DataUtils.getCyclicDataMap(size));
        System.out.println(cyclicDataMap);
    }

    @Test
    @Override
    public void testMyDataAndCyclicDataMapToString() {
        myDataAndCyclicDataMap = gson.toJson(DataUtils.getMyDataAndCyclicDataMap(size));
        System.out.println(myDataAndCyclicDataMap);
    }


    @Test
    @Override
    public void zTestStringListObjStringToObj() {
        System.out.println(gson.fromJson(stringList, List.class));
    }


    @Test
    @Override
    public void zTestStringMapObjStringToObj() {
        System.out.println(gson.fromJson(stringMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataStringToObj() {
        System.out.println(gson.fromJson(myData, MyData.class));
    }

    @Test
    @Override
    public void zTestMyDataListStringToObj() {
        System.out.println(gson.fromJson(myDataList, List.class));
    }

    @Test
    @Override
    public void zTestMyDataMapStringToObj() {
        System.out.println(gson.fromJson(myDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestCyclicDataStringToObj() {
        System.out.println(gson.fromJson(cyclicData, CyclicData.class));
    }

    @Test
    @Override
    public void zTestCyclicDataListStringToObj() {
        System.out.println(gson.fromJson(cyclicDataList, List.class));
    }

    @Test
    @Override
    public void zTestCyclicDataMapStringToObj() {
        System.out.println(gson.fromJson(cyclicDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataAndCyclicDataMapStringToObj() {
        System.out.println(gson.fromJson(myDataAndCyclicDataMap, Map.class));
    }


}
