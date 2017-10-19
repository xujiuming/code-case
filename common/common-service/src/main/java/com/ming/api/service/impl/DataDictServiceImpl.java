package com.ming.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ming.api.service.DataDictService;
import com.ming.entity.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class DataDictServiceImpl implements DataDictService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public DataDict all() {
        //长时间休眠  触发熔断
        return new DataDict(1L, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
    }

    @Value("${from}")
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public DataDict findDictById(@RequestParam("id") Long id) {
        //长时间休眠  触发熔断
        //return new DataDict(id, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
        return new DataDict(id,from);
    }

    @Override
    public List<DataDict> findDictListByIds(@RequestParam("ids") Collection<Long> ids) {
        //长时间休眠  触发熔断
        List<DataDict> result = Lists.newArrayList();
        for (Long id : ids) {
            result.add(new DataDict(id, JSON.toJSONString(discoveryClient.getLocalServiceInstance())));
        }
        return result;
    }


}
