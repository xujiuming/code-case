package com.ming.test.jdk8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ming
 * @date 2017-10-31 11:30
 */
public class Test {
    @org.junit.Test
    public void test() {
        Set<A> aSet1 = new HashSet<>();
        aSet1.add(new A("a", 1));
        Set<A> aSet2 = new HashSet<>();
        aSet2.add(new A("a", 1));
        System.out.println("未重写 eq  和hashCode---------");
        System.out.println(aSet1.equals(aSet2));
        System.out.println();

        Set<B> bSet1 = new HashSet<>();
        bSet1.add(new B("b", 1));
        Set<B> bSet2 = new HashSet<>();
        bSet2.add(new B("b", 1));
        System.out.println("重写 eq ----------");
        System.out.println(bSet1.equals(bSet2));
        System.out.println();

        Set<C> cSet1 = new HashSet<>();
        cSet1.add(new C("c", 1));
        Set<C> cSet2 = new HashSet<>();
        cSet2.add(new C("c", 1));
        System.out.println("重写 hashCode ------");
        System.out.println(cSet1.equals(cSet2));
        System.out.println();

        Set<D> dSet1 = new HashSet<>();
        dSet1.add(new D("d", 1));
        Set<D> dSet2 = new HashSet<>();
        dSet2.add(new D("d", 1));
        System.out.println("重写 eq  和hashCode----");
        System.out.println(dSet1.equals(dSet2));
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class A {
    private String aName;
    private Integer aNum;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class B {
    private String bName;
    private Integer bNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B b = (B) o;

        if (bName != null ? !bName.equals(b.bName) : b.bName != null) return false;
        return bNum != null ? bNum.equals(b.bNum) : b.bNum == null;
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class C {
    private String cName;
    private Integer cNum;


    @Override
    public int hashCode() {
        int result = cName != null ? cName.hashCode() : 0;
        result = 31 * result + (cNum != null ? cNum.hashCode() : 0);
        return result;
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class D {
    private String dName;
    private Integer dNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        D d = (D) o;

        if (dName != null ? !dName.equals(d.dName) : d.dName != null) return false;
        return dNum != null ? dNum.equals(d.dNum) : d.dNum == null;
    }

    @Override
    public int hashCode() {
        int result = dName != null ? dName.hashCode() : 0;
        result = 31 * result + (dNum != null ? dNum.hashCode() : 0);
        return result;
    }
}
