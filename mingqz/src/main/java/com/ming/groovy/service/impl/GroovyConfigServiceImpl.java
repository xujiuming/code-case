package com.ming.groovy.service.impl;

import com.ming.base.BaseService;
import com.ming.core.utils.GroovyUtils;
import com.ming.core.utils.SpringBeanManager;
import com.ming.groovy.entity.GroovyJobConfig;
import com.ming.groovy.repository.GroovyJobConfigRepository;
import com.ming.groovy.service.api.GroovyConfigService;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * groovy 服务实现
 *
 * @author ming
 * @date 2017-11-13 17:21
 */
@Service
public class GroovyConfigServiceImpl extends BaseService implements GroovyConfigService {

    @Autowired
    private GroovyJobConfigRepository groovyJobConfigRepository;

    @Override
    public Map<String, Class<?>> findGroovyScriptBeanList() {
        return GroovyUtils.GROOVY_SCRIPT_BEAN_MAP;
    }


    @Override
    public GroovyJobConfig save(GroovyJobConfig groovyJobConfig) {
        try {
            SpringBeanManager.registerBean(groovyJobConfig.getBeanName(), GroovyUtils.newInsta().getClass());
            groovyJobConfig.setRegisterStatus(GroovyJobConfig.RegisterStatus.REGISTER.getKey());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        groovyJobConfig.setStatus(GroovyJobConfig.Status.VALID.getKey());
        return groovyJobConfigRepository.save(groovyJobConfig);
    }

    @Override
    public GroovyJobConfig detail(Long id) {
        return groovyJobConfigRepository.findOne(id);
    }

    @Override
    public Page<GroovyJobConfig> findPage(Pageable pageable) {
        return groovyJobConfigRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        GroovyJobConfig groovyJobConfig = groovyJobConfigRepository.findOne(id);
        groovyJobConfig.setStatus(GroovyJobConfig.Status.IN_VALID.getKey());
        groovyJobConfigRepository.save(groovyJobConfig);
    }


    @Override
    public List<GroovyJobConfig> findRegisterListByNotBeanNameSet(Set<String> beanNames) {
        return groovyJobConfigRepository.findAllByRegisterStatusEqualsAndBeanNameNotIn(GroovyJobConfig.RegisterStatus.REGISTER.getKey(), beanNames);
    }

    @Override
    public List<GroovyJobConfig> findRegisterList() {
        return groovyJobConfigRepository.findAllByRegisterStatusEqualsAndBeanNameNotIn(GroovyJobConfig.RegisterStatus.REGISTER.getKey(), Sets.newHashSet());
    }
}
