package com.ming.controller.image;

import com.ming.base.orm.PageParam;
import com.ming.entity.image.ImageBaseInfo;
import com.ming.service.image.ImageBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ming
 * @date 2017-08-20
 */
@Controller
@RequestMapping(value = "image")
public class ImageController {

    @Autowired
    private ImageBaseInfoService imageBaseInfoService;


    @GetMapping(value = "/page")
    @ResponseBody
    public Page<ImageBaseInfo> imagePage(PageParam pageParam) {
        return imageBaseInfoService.findPage(pageParam);
    }

}
