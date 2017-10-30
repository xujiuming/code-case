package com.ming.api.service.client;

import com.ming.api.service.DataDictService;
import com.ming.api.service.LogService;
import org.springframework.cloud.netflix.feign.FeignClient;
/**
 *common 服务接口声明
 *@author ming
 *@date 2017-10-30 11:22
 */
public interface CommonServiceClient {

    @FeignClient(value = "COMMON-SERVICE")
    interface DataDictServiceClient extends DataDictService {
    }

    @FeignClient(value = "COMMON-SERVICE")
    interface LogServiceClient extends LogService {
    }
}


