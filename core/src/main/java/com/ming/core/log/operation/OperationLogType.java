package com.ming.core.log.operation;

import com.ming.core.orm.InId;
import lombok.Data;

/**
 *操作日志 类型
 *@author ming
 *@date 2017-10-30 12:52
 */
@Data
public class OperationLogType extends InId<Long> {
    /**
     * 业务模块 {@linkplain com.ming.core.log.operation.constants.ModuleType 业务模块类型 }
     */
    private Integer moduleType;
    /**
     * 操作类型
     */
    private Integer operateType;
    /**
     * 操作平台类型
     */
    private Integer systemType;

    /**
     * 组织机构id
     */
    private Long orgId;


}
