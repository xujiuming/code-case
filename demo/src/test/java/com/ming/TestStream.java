package com.ming;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试 stream 用法
 *
 * @author ming
 * @date 2018-06-26 15:45:08
 */
public class TestStream {


    /**
     * 将List<T1>  转换成 Map<id,T1>
     *
     * @author ming
     * @date 2018-06-26 15:54:54
     */
    @Test
    public void listToMap() {
        List<T1> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new T1("id" + i, "name" + i));
        }
        //转换成 id 为key  T1为value的map
        Map<String, T1> map = list.stream().collect(Collectors.toMap(T1::getId, t -> t));
        System.out.println(map);
        //当出现重复值 按照 (oV, nV) -> nV 来选择新的value
        Map<String, T1> map1 = list.stream().collect(Collectors.toMap(T1::getId, t -> t, (oV, nV) -> nV));
        System.out.println(map1);
    }


    /**
     * 获取List<T2> 中的t1的list的合集
     *
     * @author ming
     * @date 2018-06-26 16:02:35
     */
    @Test
    public void ListToFlatList() {
        List<T2> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<T1> t1List = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                t1List.add(new T1("id" + i, "name" + i));
            }
            list.add(new T2("id" + i, "name" + i, t1List));
        }
        List<T1> resultList = list.stream().flatMap(f -> f.getT1List().stream()).collect(Collectors.toList());
        System.out.println(resultList);
    }

    /**
     * 将 List<T3>中的num进行累加计数
     *
     * @author ming
     * @date 2018-06-26 16:06:24
     */
    @Test
    public void numReduce() {
        List<T3> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new T3("id" + i, i));
        }
        Integer countNum = list.stream().map(T3::getNum).reduce(0, (sum, item) -> sum + item);
        System.out.println(countNum);
        Integer countNum1 = list.stream().map(T3::getNum).reduce(0, Integer::sum);
        System.out.println(countNum1);
    }


    /**
     * 将List<T4>按照id 分组并且 累加price
     * 分两种方案
     * 一是先分组 然后map->reduce
     * 二直接分组 进行reduce 取巧进行对象的累加
     *
     * @author ming
     * @date 2018-07-05 09:58:01
     */
    @Test
    public void testGroupByAndReduce() {
        List<T4> list = new ArrayList<>();
        list.add(new T4(1, BigDecimal.valueOf(1)));
        list.add(new T4(1, BigDecimal.valueOf(10)));
        list.add(new T4(2, BigDecimal.valueOf(1)));
        list.add(new T4(2, BigDecimal.valueOf(10)));
/*

        //方案一 先分组 然后迭代处理
        Map<Integer, BigDecimal> result = new HashMap<>();
        list.stream().collect(Collectors.groupingBy(T4::getId, Collectors.toSet()))
                .forEach((k, v) -> {
                    result.put(k, v.stream().map(T4::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
                });
        System.out.println(result);
*/

/*

        //方案二 使用取巧的方案 进行对象累加  这样 分组id不变 而且内部的属性也可以按照自己的定义去计算
        Map<Integer, T4> result = list.stream()
                .collect(Collectors.groupingBy(T4::getId
                        , Collectors.reducing(new T4(1, BigDecimal.ZERO), (o, item) -> new T4(o.getId(), o.getPrice().add(item.getPrice())))));
        System.out.println(result);
*/
    }

}

class T1 {
    private String id;
    private String name;


    public T1() {
    }

    public T1(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class T2 {
    private String id;
    private String name;
    private List<T1> t1List;

    public T2() {
    }

    public T2(String id, String name, List<T1> t1List) {
        this.id = id;
        this.name = name;
        this.t1List = t1List;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T1> getT1List() {
        return t1List;
    }

    public void setT1List(List<T1> t1List) {
        this.t1List = t1List;
    }
}

class T3 {
    private String id;
    private Integer num;


    public T3() {
    }

    public T3(String id, Integer num) {
        this.id = id;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}


class T4 {
    private Integer id;
    private BigDecimal price;

    public T4() {
    }

    public T4(Integer id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "T4{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}