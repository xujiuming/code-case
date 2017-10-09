package com.ming.consumer;

import com.ming.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class DictConsumerController {


    @Autowired
    private DictService dictService;

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
    public Observable<String> asyncDictToObservable() {
        return dictService.observableDictAll();
    }


    @GetMapping(value = "/dict/observe")
    public Observable<String> dictObserve() throws InterruptedException, TimeoutException, ExecutionException {
        return dictService.dictByObservableCommandObserve();
    }

    @GetMapping(value = "/dict/to-observable")
    public Observable<String> dictToObservable() throws InterruptedException, TimeoutException, ExecutionException {
        return dictService.dictByObservableCommandToObservable();
    }


    @GetMapping(value = "/dict/command-execute")
    public String dictCommandExecute() {
        return dictService.dictByCommandExecute();
    }

    @GetMapping(value = "/dict/command-queue")
    public String dictCommandQueue() throws ExecutionException, InterruptedException {
        return dictService.dictByCommandQueue();
    }

}
