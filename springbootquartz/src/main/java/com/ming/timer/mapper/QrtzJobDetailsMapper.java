package com.ming.timer.mapper;

import java.util.Collection;


/**
 * 脚本自动生成 mapper
 *
 * @author ming
 * @date 2018-04-26 13:37:54
 */
public interface QrtzJobDetailsMapper {
    /**
     * 根据id查询
     *
     * @param id
     * @return QrtzJobDetails
     * @author ming
     * @date 2018-04-26 14:16
     */
    QrtzJobDetails findById(Integer id);

    /**
     * 查询所有的
     *
     * @return Collection<QrtzJobDetails>
     * @author ming
     * @date 2018-04-26 14:16
     */
    Collection<QrtzJobDetails> findAll();


}