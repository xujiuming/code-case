package com.ming.core.log.operation;

import com.ming.core.orm.InId;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

/**
 *业务日志 基本信息 实体
 *@author ming
 *@date 2017-10-30 11:35
 */
@Data
public class OperationLog extends InId<Long> {
 public static final Integer OPERATION_LOG_RULE_NUM = 10000;
    /**
     * 操作用户编号
     */
    private Long userId;

    /**
     * 操作内容  json 字符串
     */
    private String content;
    /**
     * 扩展参数
     * 附加参数信息 map 的json 字符串
     */
    private String extraString;

    /**
     * UA
     */
    private String ua;
    /**
     * ip地址
     */
    private String ip;

    /**
     *操作 日志类型  所有操作级联  根据id维护
     */
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "id",optional = false)
    private OperationLogType operationLogType;

}
