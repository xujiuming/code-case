package com.ming.base.orm;

import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * 使用自实现的命名转换
 *
 * @author ming
 * @date 2017-11-09 15:43
 */
public class MyPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    /**
     * 重写此方法  避免 转换小写 因为 mysql 5.7 表名是区分大小写的
     *
     * @author ming
     * @date 2017-11-09 15:43
     */
    @Override
    protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
        return false;
    }
}
