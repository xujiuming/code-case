package com.ming.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Deprecated
public class LogServiceX {
    @Autowired
    private RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "findByIds"
            , collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")}
            , scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL)
    //直接写log 对象 map无法转换成log对象  因为findByIds获取结果的时候 格式是List<Map>方式返回 而不是 List<Log>
    public Object findById(Long id) {
        throw new RuntimeException("findById由 findByIds统一执行");
        //return  restTemplate.getForObject("http://COMMON-SERVICE/log/detail?id="+id,Log.class);
    }

    @HystrixCommand
    public List<Object> findByIds(List<Long> ids) {
        StringBuffer sb = new StringBuffer();
        for (Long id : ids) {
            sb.append("ids=");
            sb.append(id);
            sb.append("&");
        }
        return restTemplate.getForObject("http://COMMON-SERVICE/log/list?" + sb.toString(), List.class);
    }
}
