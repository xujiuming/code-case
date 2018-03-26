package com.ming.test;

import com.ming.Start;
import groovy.lang.GroovyShell;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by xianyu on 17-10-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Start.class)
public class Test {


    public static void main(String[] args) {
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.parse("print 'nihao'").run();
    }
}
