package com.ming.base.orm.jpa;

import com.ming.entity.InId;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    //父类没有不带参数的构造方法，这里手动构造父类
    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<T> findListByNativeSql(String sql, Class<T> clzss) {
        return entityManager.createNativeQuery(sql, clzss).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends InId> List<T> findListById(Long id, T t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<? extends InId> query = criteriaBuilder.createQuery(t.getClass());
        Root<? extends InId> root = query.from(t.getClass());
        Predicate predicate = criteriaBuilder.equal(root.get(String.valueOf(t.getId())), id);
        query.where(predicate);
        return (List<T>) entityManager.createQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends InId> List<T> findListByIds(Collection<Long> ids, T t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<? extends InId> query = criteriaBuilder.createQuery(t.getClass());
        Root<? extends InId> root = query.from(t.getClass());
        Predicate predicate = criteriaBuilder.in(root.get(String.valueOf(t.getId())));
        query.where(predicate);
        return (List<T>) entityManager.createQuery(query).getResultList();
    }
}
