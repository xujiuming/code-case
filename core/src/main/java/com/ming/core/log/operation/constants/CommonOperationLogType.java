package com.ming.core.log.operation.constants;

import com.ming.core.log.operation.OperationLog;

public enum CommonOperationLogType implements OperationType {
    CREATE_DATA_DICT(ModuleType.COMMON.getType() * OperationLog.OPERATION_LOG_RULE_NUM + 1),
    UPDATE_DATA_DICT(ModuleType.COMMON.getType() * OperationLog.OPERATION_LOG_RULE_NUM + 2);


    private Integer value;

    CommonOperationLogType(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getMudoleType() {
        return ModuleType.COMMON.getType();
    }

    @Override
    public Integer getLogValue() {
        return value;
    }
}
