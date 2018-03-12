package com.ming.base.config;

import com.ming.Start;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger2 设定
 *
 * @author com.ming
 * @date 2017-12-13 19:01
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口列表")
                        .description("接口列表")
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Start.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * swagger 代理控制器
     *
     * @author ming
     * @date 2018-01-16 14:19
     */
    @Controller
    public class SwaggerController {

        /**
         * swagger代理控制器
         *
         * @author ming
         * @date 2018-01-16 14:20
         */
        @RequestMapping(value = "swagger")
        public String swagger() {
            return "redirect:/swagger/index.html";
        }
    }

}

