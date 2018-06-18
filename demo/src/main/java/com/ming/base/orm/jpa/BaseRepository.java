package com.ming.base.orm.jpa;

import com.ming.base.orm.InId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基于jpa的默认jpaRepository实现自己的repository接口
 * NoRepositoryBean 不会创建接口的实例
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 使用原生sql 查询 list列表
     *
     * @author ming
     * @date 2017-08-29 16点
     */
    List<T> findListByNativeSql(String sql, Class<T> clzss);

    <T extends InId> List<T> findListById(Long id, T t);

    <T extends InId> List<T> findListByIds(Collection<Long> ids, T t);


}
