package com.ming.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据工具类 用来获取数据 提供给各种json框架解析
 *
 * @author ming
 * @date 2018-07-17 15:30:30
 */
public class DataUtils {

    /**
     * 获取String list
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:08:50
     */
    public static List<String> getStringList(int size) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            resultList.add("test" + i);
        }
        return resultList;
    }


    /**
     * 获取String  String map
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:09:04
     */
    public static Map<String, String> getStringMap(int size) {
        Map<String, String> resultMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            resultMap.put("k" + i, "v" + i);
        }
        return resultMap;
    }

    /**
     * 获取 自定义对象
     *
     * @author ming
     * @date 2018-07-17 16:09:20
     */
    public static MyData getMyData() {
        MyData myData = new MyData();
        myData.setId(1);
        myData.setName("ming");
        myData.setAge(BigDecimal.TEN);
        return myData;
    }

    /**
     * 获取自定义对象 list
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:09:30
     */
    public static List<MyData> getMyDataList(int size) {
        List<MyData> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MyData tmp = new MyData();
            tmp.setId(i);
            tmp.setName("ming" + i);
            tmp.setAge(BigDecimal.valueOf(i));
            resultList.add(tmp);
        }
        return resultList;
    }

    /**
     * 获取String  自定义对象 map
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:09:47
     */
    public static Map<String, MyData> getMyDataMap(int size) {
        Map<String, MyData> resultMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            MyData tmp = new MyData();
            tmp.setId(i);
            tmp.setName("ming" + i);
            tmp.setAge(BigDecimal.valueOf(i));
            resultMap.put("k" + i, tmp);
        }
        return resultMap;
    }

    /**
     * 获取内嵌对象的对象
     *
     * @author ming
     * @date 2018-07-17 16:10:03
     */

    public static CyclicData getCyclicData() {
        CyclicData result = new CyclicData();
        result.setId(1);

        CyclicData tmp = new CyclicData();
        tmp.setId(2);
        result.setCyclicData(tmp);

        return result;
    }

    /**
     * 获取内嵌对象的对象 的list
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:10:17
     */
    public static List<CyclicData> getCyclicDataList(int size) {
        List<CyclicData> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CyclicData tmp = new CyclicData();
            tmp.setId(i);

            CyclicData t = new CyclicData();
            tmp.setId(i * 1000);
            tmp.setCyclicData(t);
            resultList.add(tmp);
        }
        return resultList;
    }

    /**
     * 获取内嵌对象的对象的map
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:10:32
     */
    public static Map<String, CyclicData> getCyclicDataMap(int size) {
        Map<String, CyclicData> resultMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            CyclicData tmp = new CyclicData();
            tmp.setId(i);

            CyclicData t = new CyclicData();
            tmp.setId(i * 1000);
            tmp.setCyclicData(t);
            resultMap.put("k" + i, tmp);
        }
        return resultMap;
    }


    /**
     * 获取 kv 都是对象的map
     *
     * @param size
     * @author ming
     * @date 2018-07-17 16:10:49
     */
    public static Map<MyData, CyclicData> getMyDataAndCyclicDataMap(int size) {
        Map<MyData, CyclicData> resultMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            CyclicData tmp = new CyclicData();
            tmp.setId(i);

            CyclicData t = new CyclicData();
            tmp.setId(i * 1000);
            tmp.setCyclicData(t);


            MyData k = new MyData();
            k.setId(i);
            k.setName("ming" + i);
            k.setAge(BigDecimal.valueOf(i));

            resultMap.put(k, tmp);
        }


        return resultMap;
    }


}







