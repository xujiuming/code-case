package com.ming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestCommonController {
    @Autowired
    private ICommonTestService iCommonTestService;

    @GetMapping("/user-test-common")
    public String userTestCommon() {
        return iCommonTestService.testCommon("userTest");
    }
}
