package com.ming.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ming.api.service.DataDictService;
import com.ming.config.Config;
import com.ming.entity.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * 数据字典服务实现
 *
 * @author ming
 * @date 2017-10-30 11:17
 */
@RefreshScope
@RestController
public class DataDictServiceImpl implements DataDictService {


    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Environment environment;
    @Value("${from}")
    private String from;
    @Autowired
    private Config config;

    @Override
    public DataDict all() {
        //长时间休眠  触发熔断
        return new DataDict(1L, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
    }

    @Override
    public DataDict findDictById(@RequestParam("id") Long id) {
        //长时间休眠  触发熔断
        //return new DataDict(id, JSON.toJSONString(discoveryClient.getLocalServiceInstance()));
        return new DataDict(id, from);
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
