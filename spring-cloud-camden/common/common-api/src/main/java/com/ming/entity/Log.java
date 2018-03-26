package com.ming.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志实体
 *
 * @author ming
 * @date 2017-10-30 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    private Long id;
    private String name;
}
