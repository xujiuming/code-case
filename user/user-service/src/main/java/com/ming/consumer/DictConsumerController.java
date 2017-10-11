package com.ming.consumer;

import com.ming.entity.Dict;
import com.ming.service.CommonFeignClient;
import com.ming.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class DictConsumerController {


    @Autowired
    private DictService dictService;

    @Autowired
    private CommonFeignClient.DictFeignClient dictRefactorService;


    @GetMapping(value = "/dict")
    public String dict() throws InterruptedException {
        System.out.println(1111111);
        return dictService.dictAll();
    }

    @GetMapping(value = "/async-dict")
    public String asyncDict() throws InterruptedException, TimeoutException, ExecutionException {
        return dictService.asyncDictAll().get(10, TimeUnit.SECONDS);
    }

    @GetMapping(value = "/async-dict-observable")
    public String asyncDictToObservable() throws ExecutionException, InterruptedException {
        return dictService.observableDictAll().toBlocking().toFuture().get();
    }


    @GetMapping(value = "/dict/observe")
    public String dictObserve() throws InterruptedException, TimeoutException, ExecutionException {
        return dictService.dictByObservableCommandObserve().toBlocking().toFuture().get();
    }

    @GetMapping(value = "/dict/to-observable")
    public String dictToObservable() throws InterruptedException, TimeoutException, ExecutionException {
        return dictService.dictByObservableCommandToObservable().toBlocking().toFuture().get();
    }


    @GetMapping(value = "/dict/command-execute")
    public String dictCommandExecute() {
        return dictService.dictByCommandExecute();
    }

    @GetMapping(value = "/dict/command-queue")
    public String dictCommandQueue() throws ExecutionException, InterruptedException {
        return dictService.dictByCommandQueue();
    }


    @GetMapping(value = "dict/{id}")
    public Dict findDictById(Long id) {
        return dictService.findDictById(id);
    }

    @GetMapping(value = "dict/{ids")
    public List findDictListByIds(Collection<Long> ids) {
        return dictService.findDictListByIds(ids);
    }

    //----------------feign 调用 begin--------------

    @GetMapping(value = "feign-dict/all")
    public Dict findDict() {
        return dictRefactorService.all();
    }

    @GetMapping(value = "feign-dict/detail")
    public Dict findDictByIdFeign(Long id) {
        return dictRefactorService.findDictById(id);
    }


    @GetMapping(value = "feign-dict/list")
    public List<Dict> findDictByIdsFeign(@RequestParam("ids") List<Long> ids) {
        return dictRefactorService.findDictListByIds(ids);
    }

    //----------------feign 调用 end--------------


}
