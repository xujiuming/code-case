package com.ming.test;


import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TestUserService {

    private static final RestTemplate rest = new RestTemplate();


    @Test
    public void test() {
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("userName", Lists.newArrayList("ming"));
        map.put("password", Lists.newArrayList("ming"));
        map.put("phone", Lists.newArrayList("111111122222"));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        for (int i = 0; i < 100; i++) {
            System.out.println(rest.postForEntity("http://localhost:8080/test/add", requestEntity, String.class));
        }


    }


}
