package com.ming.api.service;

import com.ming.entity.DataDict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@RequestMapping("dict")
public interface DataDictService {
    @RequestMapping(value = "all", method = RequestMethod.GET)
    DataDict all();

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    DataDict findDictById(@RequestParam("id") Long id);

    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<DataDict> findDictListByIds(@RequestParam("ids") Collection<Long> ids);
}
