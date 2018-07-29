package com.ming.timer.base.orm;

import com.google.common.collect.Maps;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 根据data jpa 相关使用规则 封装一个pageParam对象
 *
 * @author ming
 * @date 2017-11-06 16:29
 */
public class PageParam implements Serializable {
    /**
     * 第几页
     */
    private Integer number;
    /**
     * 每页大小
     */
    private Integer size;
    /**
     * 排序规则
     */
    private Sort sort;
    /**
     * 过滤参数
     */
    private Map<String, Object> filter;
    /**
     * 查询参数
     */
    private Collection<SearchFilter> searchFilters;


    /**
     * 默认值
     */
    private Integer defaultNumber = 1;
    private Integer defaultSize = 15;
    private Sort defaultSort = new Sort(Sort.Direction.DESC, "id");

    /**
     * 获取pageable
     *
     * @return Pageable
     * @author ming
     * @date 2017-11-06 16:28
     */
    public Pageable getPageable() {
        return new PageRequest(getNumber(), getSize(), getSort());
    }

    /**
     * 获取 Map<String,SearchFilter>
     *
     * @return Map
     * @author ming
     * @date 2017-11-06 16:34
     */
    public Map<String, SearchFilter> getSearchFilterMap() {
        if (filter == null) {
            return Maps.newHashMap();
        }
        return SearchFilter.parse(filter);
    }

    public Collection<SearchFilter> getSearchFilters() {
        return getSearchFilterMap().values();
    }

    public Integer getNumber() {
        if (number == null) {
            number = defaultNumber;
        }
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        if (size == null) {
            size = defaultSize;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort getSort() {
        if (sort == null) {
            sort = defaultSort;
        }
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {

        this.filter = filter;
    }


}
