package com.ming.test;

import com.ming.groovy.entity.GroovyJobConfig;
import org.springframework.web.client.RestTemplate;

public class Test {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        long now = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            System.out.println(restTemplate.getForEntity("http://localhost:8080/test", String.class));
        }
        System.out.println("消耗ms:" + (System.currentTimeMillis() - now));
    }


    @org.junit.Test
    public void test() {
        GroovyJobConfig groovyJobConfig = new GroovyJobConfig();
        groovyJobConfig.setId(1L);
        groovyJobConfig.setName("ming");

        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("------------------------------");

    }
}
