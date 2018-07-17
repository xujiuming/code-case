package com.ming.json;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 聚合多个测试用例 在一起测试
 *
 * @author ming
 * @date 2018-07-17 17:31:55
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestFastJson.class, TestJackson.class, TestGson.class, TestJsonLib.class, TestGenson.class})
public class AllTest {
}
