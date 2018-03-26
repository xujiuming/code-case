package com.ming.groovy.controller;

import com.google.common.base.Preconditions;
import com.ming.base.BaseController;
import com.ming.base.orm.PageParam;
import com.ming.groovy.entity.GroovyJobConfig;
import com.ming.groovy.service.api.GroovyConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * groovy job 相关接口
 *
 * @author ming
 * @date 2017-11-13 17:21
 */
@RestController
@RequestMapping("groovy-job")
public class GroovyJobController extends BaseController {
    @Autowired
    private GroovyConfigService groovyService;

    /**
     * 跳转 编辑页面
     *
     * @param id
     * @param model
     * @author ming
     * @date 2017-11-14 14:17
     */
    @GetMapping(value = "detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String detail(Model model, @RequestParam Long id) {
        Preconditions.checkNotNull(id, "id不能为空");
        return "groovy-job/edit";
    }

    /**
     * 创建
     *
     * @author ming
     * @date 2017-11-14 14:17
     */
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String create(@RequestBody GroovyJobConfig groovyJobConfig) {
        groovyJobConfig.checkCreate();
        groovyService.save(groovyJobConfig);
        return "groovy-job/page";
    }

    /**
     * 删除
     *
     * @param id
     * @author ming
     * @date 2017-11-14 14:17
     */
    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(@RequestParam Long id) {
        Preconditions.checkNotNull(id, "id不能为空");
        groovyService.delete(id);
        return "groovy-job/page";
    }


    /**
     * 获取分页对象
     *
     * @author ming
     * @date 2017-11-14 14:19
     */
    @GetMapping(value = "page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String page(Model model, @RequestBody PageParam pageParam) {
        return "groovy-job/page";
    }

    /**
     * 修改
     *
     * @author ming
     * @date 2017-11-14 14:19
     */
    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String update(@RequestBody GroovyJobConfig groovyJobConfig) {
        return "groovy-job/page";
    }


}
