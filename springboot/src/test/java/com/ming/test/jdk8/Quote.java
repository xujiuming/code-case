package com.ming.test.jdk8;

import lombok.Getter;

@Getter
public class Quote {

    private final String name;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String name, double price, Discount.Code discountCode) {
        this.name = name;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String str) {
        String[] string = str.split(":");
        String name = string[0];
        double price = Double.valueOf(string[1]);
        Discount.Code discountCode = Discount.Code.valueOf(string[2]);
        return new Quote(name, price, discountCode);

    }
}
