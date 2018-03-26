package com.ming.common.repository.system;

import com.ming.base.orm.jpa.BaseRepository;
import com.ming.common.entity.system.DataDict;
import org.springframework.stereotype.Repository;


/**
 * 数据字典 dao
 *
 * @author ming
 * @date 2017-12-18 11:20
 */
@Repository
public interface DataDictRepository extends BaseRepository<DataDict, Long> {
}
