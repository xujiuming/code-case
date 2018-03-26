package com.ming.base.config;

import com.ming.StartMing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger2 设定
 *
 * @author ming
 * @date 2017-12-13 19:01
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("mingqz api")
                        .description("mingqz api")
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(StartMing.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }
}

