package com.ming.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class DictCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;
    private String str;

    public DictCommand(RestTemplate restTemplate, String str) {
        super(HystrixCommand.Setter
                //设置分组key
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("mingGroupCommandKey"))
                //设置命令key
                .andCommandKey(HystrixCommandKey.Factory.asKey("mingCommandKey"))
                //设置线程池key
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("mingThreadPoolKey")));
        this.restTemplate = restTemplate;
        this.str = str;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=" + str, String.class, str);
    }


    @Override
    protected String getFallback() {
        return "command 降级";
    }


    @Override
    protected String getCacheKey() {
        return str;
    }
}

class DictGetCommand extends HystrixCommand<String> {
    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("ming");
    private RestTemplate restTemplate;
    private String str;

    public DictGetCommand(RestTemplate restTemplate, String str) {
        super(HystrixCommand.Setter
                //设置分组key
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("mingGroupCommandKey"))
                //设置命令key
                .andCommandKey(GETTER_KEY)
                //设置线程池key
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("mingThreadPoolKey")));
        this.restTemplate = restTemplate;
        this.str = str;
    }

    /**
     * 根据  cache key 清除缓存
     *
     * @author ming
     * @date 2017-10-09 17:58
     */
    public static void flushCache(String str) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(str);
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=" + str, String.class, str);
    }

    @Override
    protected String getFallback() {
        return "command 降级";
    }

    //根据str 写入缓存
    @Override
    protected String getCacheKey() {
        return str;
    }
}

class DictPostCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;
    private String str;

    public DictPostCommand(RestTemplate restTemplate, String str) {
        super(HystrixCommand.Setter
                //设置分组key
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("mingGroupCommandKey"))
                //设置命令key
                .andCommandKey(HystrixCommandKey.Factory.asKey("mingCommandKey"))
                //设置线程池key
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("mingThreadPoolKey")));
        this.restTemplate = restTemplate;
        this.str = str;
    }

    @Override
    protected String run() throws Exception {
        DictGetCommand.flushCache(str);
        return restTemplate.getForObject("http://COMMON-SERVICE/dict/all?username=" + str, String.class, str);
    }


    @Override
    protected String getFallback() {
        return "command 降级";
    }


    @Override
    protected String getCacheKey() {
        return str;
    }
}
