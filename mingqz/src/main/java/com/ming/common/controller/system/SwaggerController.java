package com.ming.common.controller.system;

import com.ming.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 代理控制器
 *
 * @author ming
 * @date 2018-01-16 14:19
 */
@Controller
public class SwaggerController extends BaseController {

    /**
     * swagger代理控制器
     *
     * @author ming
     * @date 2018-01-16 14:20
     */
    @RequestMapping(value = "swagger")
    public String swagger() {
        return "redirect:/swagger/index.html";
    }
}
