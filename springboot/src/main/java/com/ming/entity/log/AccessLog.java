package com.ming.entity.log;

import com.ming.entity.InId;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 访问日志实体
 *
 * @author ming
 * @date 2017-11-06 11:53
 */
@Data
@Entity
public class AccessLog extends InId {
    /**
     * 请求地址
     */
    private String uri;
    /**
     * 参数
     */
    private String queryString;

    /**
     * 用户编号.记录组合
     */
    private Long uid;
    /**
     * 用户类型.枚举：
     */
    private Integer userType;

    /**
     * token
     */
    private String token;

    /**
     * IP
     */
    private String ip;
    /**
     * User Agent
     */
    private String ua;


}
