package com.ming.api.service.client;

import com.ming.api.service.DataDictService;
import com.ming.api.service.LogService;
import org.springframework.cloud.netflix.feign.FeignClient;

public interface CommonServiceClient {

    @FeignClient(value = "COMMON-SERVICE")
    interface DataDictServiceClient extends DataDictService {
    }

    @FeignClient(value = "COMMON-SERVICE")
    interface LogServiceClient extends LogService {
    }
}


