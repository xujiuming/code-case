package com.ming.server;

import com.ming.entity.Dict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface DictService {
    @RequestMapping("/dict/all")
    Dict all();

    @RequestMapping("/dict/detail")
    Dict findDictById(@RequestParam("id") Long id);

    @RequestMapping("/dict/list")
    List<Dict> findDictListByIds(@RequestParam("ids") List<Long> ids);
}
