package com.ming.core.log.operation.constants;

/**
 * 操作日志 模块类型 枚举
 *
 * @author ming
 * @date 2017-10-30 12:58
 */
public enum ModuleType {
    COMMON(1), USER(2);

    private Integer type;

    ModuleType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
