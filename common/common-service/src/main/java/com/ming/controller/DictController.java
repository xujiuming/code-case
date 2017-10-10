package com.ming.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ming.entity.Dict;
import com.ming.server.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
public class DictController implements DictService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "all")
    public Dict all() {
        //长时间休眠  触发熔断
        //Thread.sleep(30000);
        return new Dict(1L, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
    }

    @GetMapping(value = "detail")
    public Dict findDictById( @RequestParam("id") Long id) {
        //长时间休眠  触发熔断
        //Thread.sleep(30000);
        return new Dict(id, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
    }

    @GetMapping(value = "list")
    public List<Dict> findDictListByIds(@RequestParam("ids") List<Long> ids) {
        //长时间休眠  触发熔断
        //Thread.sleep(30000);
        List<Dict> result = Lists.newArrayList();
        for (Long id : ids) {
            result.add(new Dict(id, JSON.toJSONString(discoveryClient.getLocalServiceInstance())));
        }
        return result;
    }


}
