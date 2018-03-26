package com.ming.controller.system;

import com.ming.base.annotations.Layout;
import com.ming.base.orm.PageParam;
import com.ming.service.image.ImageBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页跳转控制器
 *
 * @author ming
 * @date 2017-09-02
 */
@Controller
public class IndexController {
    @Autowired
    private ImageBaseInfoService imageBaseInfoService;

    @GetMapping(value = {"", "/", "index"})
    public String index(Model model, PageParam pageParam) {
        model.addAttribute("page", imageBaseInfoService.findPage(pageParam));
        return "index";
    }
}
