package com.ming.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ming.api.user.IAccountController;
import com.ming.command.LogBatchCommand;
import com.ming.command.LogCollapseCommand;
import com.ming.entity.Log;
import com.ming.service.CommonFeignClient;
import com.ming.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("account")
public class AccountController implements IAccountController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CommonFeignClient.DictFeignClient dictFeignClient;
    @Autowired
    private CommonFeignClient.LogFeignClient logFeignClient;
    @Autowired
    private LogService logService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "login")
    @Override
    public String login(String username) {
        System.out.println(username);
        //长时间休眠  触发熔断
        //Thread.sleep(30000);
        return username + JSON.toJSONString(discoveryClient.getLocalServiceInstance());
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public Object detail(@RequestParam("id")Long id){
        Map<String,Object> map = Maps.newHashMap();
        map.put("dict",dictFeignClient.findDictById(id));
        map.put("log",logFeignClient.findLogById(id));
        return map;
    }
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Object list(@RequestParam("ids")Collection<Long> ids){
        Map<String,Object> map = Maps.newHashMap();
        map.put("dict",dictFeignClient.findDictListByIds(ids));
        map.put("log",logFeignClient.findLogListByIds(ids));
        return map;
    }

    @RequestMapping(value = "log",method = RequestMethod.GET)
    public Object get(Long id) throws ExecutionException, InterruptedException {
        return  logService.findById(id);
    }

    @RequestMapping(value = "command-log",method = RequestMethod.GET)
    public Object getCommand(Long id) throws ExecutionException, InterruptedException {
        //return new LogCollapseCommand(restTemplate,id).queue().get();
        return new LogBatchCommand(restTemplate, Lists.newArrayList(1L,2L)).queue().get();
    }

}
