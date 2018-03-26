package com.ming.common.controller.system;

import com.ming.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页 控制器
 *
 * @author ming
 * @date 2017-11-08 10:33
 */
@RestController
@Api(value = "默认检测接口")
public class IndexController extends BaseController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String test() {
        return "ok";
    }
}
