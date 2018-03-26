package com.ming;

import com.ming.base.orm.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * 启动类 要位于最顶层包  他只会扫描 同级包和子包
 *
 * @author ming
 * @date 2017-06-17
 */
@SpringBootApplication(scanBasePackages = "com.ming", excludeName = {"classpath*:application.yml",
        "classpath*:application-aliyun.yml"})
// jpa使用自定义加载工厂
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableAutoConfiguration
public class Start extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Start.class);
    }
}
