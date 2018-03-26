package com.ming.api.service;

import com.ming.entity.DataDict;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * 数据字段 接口
 *
 * @author ming
 * @date 2017-10-30 11:16
 */
@RefreshScope
@RequestMapping("dict")
public interface DataDictService {
    /**
     * 获取所有有效数据字典 信息
     *
     * @author ming
     * @date 2017-10-30 11:29
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    DataDict all();

    /**
     * 根据id 获取有效数据字典信息
     *
     * @param id
     * @author ming
     * @date 2017-10-30 11:30
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    DataDict findDictById(@RequestParam("id") Long id);

    /**
     * 根据ids 获取有效的数据字典信息
     *
     * @param ids
     * @author ming
     * @date 2017-10-30 11:30
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<DataDict> findDictListByIds(@RequestParam("ids") Collection<Long> ids);
}
