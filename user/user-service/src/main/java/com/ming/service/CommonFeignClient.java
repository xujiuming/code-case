package com.ming.service;

import com.ming.server.IDictController;
import com.ming.server.ILogController;
import org.springframework.cloud.netflix.feign.FeignClient;

public interface CommonFeignClient {

    @FeignClient(value = "COMMON-SERVICE")
    interface DictFeignClient extends IDictController {
    }

    @FeignClient(value = "COMMON-SERVICE")
    interface LogFeignClient extends ILogController {
    }
}


