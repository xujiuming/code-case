package com.ming;

import com.ming.timer.base.orm.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 调度中心启动类
 *
 * @author ming
 * @date 2018-03-13 14:40
 */
@SpringBootApplication
// jpa使用自定义加载工厂
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableSwagger2
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
