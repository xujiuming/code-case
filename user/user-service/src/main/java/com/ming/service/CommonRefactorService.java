package com.ming.service;

import com.ming.server.DictService;
import com.ming.server.LogService;
import org.springframework.cloud.netflix.feign.FeignClient;

public interface CommonRefactorService {

    @FeignClient(value = "COMMON-SERVICE")
    interface DictRefactorService extends DictService {}
    @FeignClient(value = "COMMON-SERVICE")
    interface LogRefactorService extends LogService {}
}


