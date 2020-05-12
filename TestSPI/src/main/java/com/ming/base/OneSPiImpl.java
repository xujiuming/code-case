package com.ming.base;

/**
 * 第一种spi接口实现
 *
 * @author ming
 * @date 2020-05-12 13:17
 */
public class OneSPiImpl implements MingSPI {
    @Override
    public void hello() {
        System.out.println("hello one");
    }
}
