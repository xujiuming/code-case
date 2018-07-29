package com.ming.json;

import com.owlike.genson.Genson;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

/**
 * 测试使用genson
 *
 * @author ming
 * @date 2018-07-17 15:24:33
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGenson extends TestJsonAbstract {
    private Genson genson = new Genson();


    @Test
    @Override
    public void testStringListObjToString() {
        stringList = genson.serialize(DataUtils.getStringList(size));
        System.out.println(stringList);
    }


    @Test
    @Override
    public void testStringMapObjToString() {
        stringMap = genson.serialize(DataUtils.getStringMap(size));
        System.out.println(stringMap);
    }

    @Test
    @Override
    public void testMyDataToString() {
        myData = genson.serialize(DataUtils.getMyData());
        System.out.println(myData);
    }

    @Test
    @Override
    public void testMyDataListToString() {
        myDataList = genson.serialize(DataUtils.getMyDataList(size));
        System.out.println(myDataList);
    }

    @Test
    @Override
    public void testMyDataMapToString() {
        myDataMap = genson.serialize(DataUtils.getMyDataMap(size));
        System.out.println(myDataMap);
    }

    @Test
    @Override
    public void testCyclicDataToString() {
        cyclicData = genson.serialize(DataUtils.getCyclicData());
        System.out.println(cyclicData);
    }

    @Test
    @Override
    public void testCyclicDataListToString() {
        cyclicDataList = genson.serialize(DataUtils.getCyclicDataList(size));
        System.out.println(cyclicDataList);
    }

    @Test
    @Override
    public void testCyclicDataMapToString() {
        cyclicDataMap = genson.serialize(DataUtils.getCyclicDataMap(size));
        System.out.println(cyclicDataMap);
    }

    @Test
    @Override
    public void testMyDataAndCyclicDataMapToString() {
        myDataAndCyclicDataMap = genson.serialize(DataUtils.getMyDataAndCyclicDataMap(size));
        System.out.println(myDataAndCyclicDataMap);
    }


    @Test
    @Override
    public void zTestStringListObjStringToObj() {
        System.out.println(genson.deserialize(stringList, List.class));
    }


    @Test
    @Override
    public void zTestStringMapObjStringToObj() {
        System.out.println(genson.deserialize(stringMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataStringToObj() {
        System.out.println(genson.deserialize(myData, MyData.class));
    }

    @Test
    @Override
    public void zTestMyDataListStringToObj() {
        System.out.println(genson.deserialize(myDataList, List.class));
    }

    @Test
    @Override
    public void zTestMyDataMapStringToObj() {
        System.out.println(genson.deserialize(myDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestCyclicDataStringToObj() {
        System.out.println(genson.deserialize(cyclicData, CyclicData.class));
    }

    @Test
    @Override
    public void zTestCyclicDataListStringToObj() {
        System.out.println(genson.deserialize(cyclicDataList, List.class));
    }

    @Test
    @Override
    public void zTestCyclicDataMapStringToObj() {
        System.out.println(genson.deserialize(cyclicDataMap, Map.class));
    }

    @Test
    @Override
    public void zTestMyDataAndCyclicDataMapStringToObj() {
        System.out.println(genson.deserialize(myDataAndCyclicDataMap, Map.class));
    }

}
