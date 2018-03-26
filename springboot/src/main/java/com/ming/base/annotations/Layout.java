package com.ming.base.annotations;

import java.lang.annotation.*;

/**
 * 样式装饰器 注解  在controller中注解
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
    /**
     * no layout will be used
     */
    String NONE = "none";

    String value();
}
