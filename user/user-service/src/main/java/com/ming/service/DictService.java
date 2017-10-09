package com.ming.service;

import com.ming.command.DictCommand;
import com.ming.command.DictObservableCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class DictService {
    @Autowired
    private RestTemplate restTemplate;

    //服务降级 顶级服务---》次级服务 v2--------》最低级别v1  是调用端 运行时间来做熔断
    //设定 这个服务使用熔断机制  熔断回调方法 是v2  忽略runtimeexception

    /**
     * 注解实现 同步访问
     *
     * @author ming
     * @date 2017-10-09 15:40
     */
    @HystrixCommand(fallbackMethod = "v2", ignoreExceptions = RuntimeException.class)
    public String dictAll() throws InterruptedException {
        //Thread.sleep(30000);
        return restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=ming", String.class);
    }

    /**
     * 通过 @HystrixCommand 实现异步访问
     *
     * @author ming
     * @date 2017-10-09 15:39
     */
    @HystrixCommand(fallbackMethod = "v2", ignoreExceptions = RuntimeException.class)
    public Future<String> asyncDictAll() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=ming", String.class);
            }
        };
    }

    /**
     * Observable 模式实现 通过 observableExecutionMode指定是
     * 默认是observe{@linkplain ObservableExecutionMode#EAGER}
     * toObservable {@linkplain ObservableExecutionMode#LAZY}
     *
     * @author ming
     * @date 2017-10-09 16:12
     */
    @HystrixCommand(fallbackMethod = "v2"
            , observableExecutionMode = ObservableExecutionMode.LAZY
            ,commandKey = "commandKey"
            ,groupKey = "groupKey"
            ,threadPoolKey = "threadPoolKey"
    )
    public Observable<String> observableDictAll() {
        return Observable.create(subscriber -> {
            try {
                if (subscriber.isUnsubscribed()) {
                    String str = restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=", String.class);
                    subscriber.onNext(str);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    /**
     * 通过自定义command 实现同步调用
     *
     * @author ming
     * @date 2017-10-09 15:38
     */
    public String dictByCommandExecute() {
        return new DictCommand(restTemplate, "ming").execute();
    }


    /**
     * 通过自定义command 实现异步调用
     *
     * @author ming
     * @date 2017-10-09 15:39
     */
    public String dictByCommandQueue() throws ExecutionException, InterruptedException {
        return new DictCommand(restTemplate, "ming").queue().get();
    }


    /**
     * 通过自定义 observableCommand 响应式
     *
     * @author ming
     * @date 2017-10-09 16:05
     */
    public Observable<String> dictByObservableCommandObserve() {
        return new DictObservableCommand(restTemplate).observe();
    }

    /**
     * 通过自定义 observableCommand 响应式
     *
     * @author ming
     * @date 2017-10-09 16:05
     */
    public Observable<String> dictByObservableCommandToObservable() {
        return new DictObservableCommand(restTemplate).toObservable();
    }


    @HystrixCommand(fallbackMethod = "v1")
    public String v2(Throwable e) throws InterruptedException {
        //降级
        //Thread.sleep(30000);
        return "v2级别 降级" + e.getMessage();
    }

    public String v1(Throwable e) {
        return "v1级别 最终级别 熔断" + e.getMessage();
    }
}
