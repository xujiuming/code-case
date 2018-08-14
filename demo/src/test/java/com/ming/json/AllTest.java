package com.ming.json;


import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聚合多个测试用例 在一起测试
 *
 * @author ming
 * @date 2018-07-17 17:31:55
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({TestJackson.class, TestGson.class,  TestGenson.class,TestFastJson.class})
public class AllTest {

    public static void main(String[] args) {
        //执行测试用例次数 通过增大执行次数 取平均数 减小误差
        int size = 10000;
        List<Map<String, Object>> result = new ArrayList<>();
        long now = System.currentTimeMillis();
        result.add(get(TestJackson.class, size));
        result.add(get(TestGson.class, size));
        result.add(get(TestGenson.class, size));
        result.add(get(TestFastJson.class, size));
        System.out.println("总耗时:" + (System.currentTimeMillis() - now) + "ms");
        System.out.println("明细信息-------------------------------");
        result.forEach(f -> {
            System.out.println("执行的测试用例" + f.get("class"));
            System.out.println("执行平均耗时" + f.get("avgTime") + "ms");
            System.out.println("每次执行的耗时详情" + f.get("testList"));
            System.out.println("--------------------------------");
        });
    }

    private static Map<String, Object> get(Class<? extends TestCase> tClass, int size) {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(tClass);
        long now;
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            now = System.currentTimeMillis();
            TestRunner.run(testSuite);
            list.add(System.currentTimeMillis() - now);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("class", tClass);
        map.put("testList", list);
        map.put("avgTime", list.stream().collect(Collectors.averagingLong(a -> a)));
        return map;
    }
}
