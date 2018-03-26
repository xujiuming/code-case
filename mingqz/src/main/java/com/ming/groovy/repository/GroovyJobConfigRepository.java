package com.ming.groovy.repository;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.groovy.entity.GroovyJobConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * groovy job dao
 *
 * @author ming
 * @date 2017-11-13 17:21
 */
@Repository
public interface GroovyJobConfigRepository extends BaseRepository<GroovyJobConfig, Long> {

    /**
     * 查询 所有名称不再set列表中的可注册的jobconfig
     *
     * @param registerStatus
     * @param beanNameSet
     * @return List<GroovyJobConfig>
     * @author ming
     * @date 2017-12-13 13:05
     */
    List<GroovyJobConfig> findAllByRegisterStatusEqualsAndBeanNameNotIn(Integer registerStatus, Set<String> beanNameSet);
}
