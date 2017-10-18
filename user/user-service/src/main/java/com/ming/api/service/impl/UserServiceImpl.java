package com.ming.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ming.api.service.LogServiceX;
import com.ming.api.service.UserService;
import com.ming.api.service.client.CommonServiceClient;
import com.ming.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("account")
public class UserServiceImpl implements UserService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CommonServiceClient.DataDictServiceClient dictServiceClient;
    @Autowired
    private CommonServiceClient.LogServiceClient logServiceClient;
    @Autowired
    private LogServiceX logServiceX;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "login")
    public String login(String username) {
        System.out.println(username);
        //长时间休眠  触发熔断
        return username + JSON.toJSONString(discoveryClient.getLocalServiceInstance());
    }

    @Override
    public User findById(Long id) {
        return new User();
    }
/*
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Object detail(@RequestParam("id") Long id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("dict", dictServiceClient.findDictById(id));
        map.put("log", logServiceClient.findLogById(id));
        return map;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(@RequestParam("ids") Collection<Long> ids) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("dict", dictServiceClient.findDictListByIds(ids));
        map.put("log", logServiceClient.findLogListByIds(ids));
        return map;
    }

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public Object get(Long id) throws ExecutionException, InterruptedException {
        return logService.findById(id);
    }

    @RequestMapping(value = "command-log", method = RequestMethod.GET)
    public Object getCommand(Long id) throws ExecutionException, InterruptedException {
        return new LogBatchCommand(restTemplate, Lists.newArrayList(1L, 2L)).queue().get();
    }*/

}
