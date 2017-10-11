package com.ming.server;

import com.ming.entity.Dict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@RequestMapping("dict")
public interface IDictController {
    @RequestMapping("all")
    Dict all();

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    Dict findDictById(@RequestParam("id") Long id);

    @RequestMapping(value = "list",method = RequestMethod.GET)
    List<Dict> findDictListByIds(@RequestParam("ids") Collection<Long> ids);
}
