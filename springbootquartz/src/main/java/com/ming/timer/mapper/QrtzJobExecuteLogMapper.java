package com.ming.timer.mapper;

import java.util.Collection;


/**
 * 脚本自动生成 mapper
 *
 * @author ming
 * @date 2018-04-26 13:12:12
 */
public interface QrtzJobExecuteLogMapper {
    /**
     * 插入数据
     *
     * @param t
     * @return int
     * @author ming
     * @date 2018-04-26 14:19
     */
    int insert(QrtzJobExecuteLog t);

    /**
     * 修改数据
     *
     * @param t
     * @return int
     * @author ming
     * @date 2018-04-26 14:19
     */
    int update(QrtzJobExecuteLog t);

    /**
     * 根据id查询
     *
     * @param id
     * @return QrtzJobExecuteLog
     * @author ming
     * @date 2018-04-26 14:19
     */
    QrtzJobExecuteLog findById(Integer id);

    /**
     * 查询所有
     *
     * @return Collection<QrtzJobExecuteLog>
     * @author ming
     * @date 2018-04-26 14:19
     */
    Collection<QrtzJobExecuteLog> findAll();

}