package com.ming.test.jdk8;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Data
public class Shop {


    /**
     * 模拟耗时操作
     *
     * @author ming
     * @date 2017-08-24 19点
     */
    public static void delay() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //模拟返回价格
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    //模拟返回商品信息
    public String getPrices(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[org.apache.commons.lang.math.RandomUtils.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }

    //异步方法 返回价格
    public Future<Double> getPriceAsync(String product) {
        //创建future对象
        CompletableFuture<Double> future = new CompletableFuture<>();
        //异步计算
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                //测试抛出异常
                //throw new RuntimeException("测试异常");
                ///设置future结果
                future.complete(price);
            } catch (Exception e) {
                //当计算异常  抛出异常 到这个future
                future.completeExceptionally(e);
            }

        }).start();
        //直接返回future对象
        return future;
    }

    /**
     * 通过CompletableFuture工厂 -----Supplier 构建异步方法
     *
     * @author ming
     * @date 2017-08-24 20点
     */
    public Future<Double> getPriceSupplyAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return RandomUtils.nextDouble() * product.charAt(0);
    }

}
