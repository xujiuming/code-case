package com.ming.base;

/**
 * 第二种实现
 *
 * @author ming
 * @date 2020-05-12 13:17
 */
public class TwoSPIImpl implements MingSPI {
    @Override
    public void hello() {
        System.out.println("hello two");
    }
}
