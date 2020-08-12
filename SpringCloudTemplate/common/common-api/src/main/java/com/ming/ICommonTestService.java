package com.ming;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "COMMON")
public interface ICommonTestService {
    @GetMapping("/common/test")
    String testCommon(@RequestParam(value = "str",required = false) String str);
}
