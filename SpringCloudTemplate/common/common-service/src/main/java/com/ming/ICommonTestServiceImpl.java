package com.ming;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ICommonTestServiceImpl implements ICommonTestService {
    @Override
    public String testCommon(String str) {
        return "common::" + str + "::" + LocalDateTime.now();
    }
}
