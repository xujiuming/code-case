package com.ming;


import com.ming.base.orm.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author ming
 * @date 2017-11-08 11:32
 */
@SpringBootApplication
// 使用jpa 自定义dao 工厂
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableCaching
public class StartMing {
    public static void main(String[] args) {
        SpringApplication.run(StartMing.class, args);
    }
}
