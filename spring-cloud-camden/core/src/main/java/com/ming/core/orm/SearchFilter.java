package com.ming.core.orm;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author ming
 * @date 2017-10-30 11:43
 */
public class SearchFilter {

    private String fieldName;
    private Object value;
    private Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

        for (Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                continue;
            }

            // 拆分operator与filedAttribute
            String[] names = StringUtils.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    public enum Operator {
        /**
         * EQ:精确比较
         * LIKE：模糊查询
         * GT:大于
         * LT:小于
         * GTE:大于或等于
         * LTE:小于或等于
         * IN:在这中间
         * NEQ：不等于
         * OR:
         */
        EQ, LIKE, GT, LT, GTE, LTE, IN, NEQ, OR
    }
}
