package com.ming;

import com.ming.base.MingSPI;

import java.util.ServiceLoader;

/**
 * 演示
 *
 * @author ming
 * @date 2020-05-12 13:19
 */
public class Start {
    public static void main(String[] args) {
        ServiceLoader<MingSPI> spiServiceLoader = ServiceLoader.load(MingSPI.class);
        for (MingSPI mingSpi : spiServiceLoader) {
            System.out.println("执行spi函数-----------");
            mingSpi.hello();
        }
    }
}
