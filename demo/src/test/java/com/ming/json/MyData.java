package com.ming.json;

import java.math.BigDecimal;

/**
 * 普通对象
 *
 * @author ming
 * @date 2018-07-17 16:11:05
 */
public class MyData {
    private Integer id;
    private String name;
    private BigDecimal age;


    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }
}
