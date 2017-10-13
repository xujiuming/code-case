package com.ming.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

public class DictObservableCommand extends HystrixObservableCommand {

    private RestTemplate restTemplate;

    public DictObservableCommand(RestTemplate restTemplate) {
        super(HystrixObservableCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ming")));
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<String> construct() {
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


}
