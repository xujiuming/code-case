package com.ming.json;


/**
 * 内嵌对象的对象
 *
 * @author ming
 * @date 2018-07-17 16:11:15
 */
public class CyclicData {
    private Integer id;
    private CyclicData cyclicData;


    @Override
    public String toString() {
        return "CyclicData{" +
                "id=" + id +
                ", cyclicData=" + cyclicData +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CyclicData getCyclicData() {
        return cyclicData;
    }

    public void setCyclicData(CyclicData cyclicData) {
        this.cyclicData = cyclicData;
    }
}
