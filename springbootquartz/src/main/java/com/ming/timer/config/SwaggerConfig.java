package com.ming.timer.config;

import com.ming.Start;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger 配置
 *
 * @author ming
 * @date 2018-04-27 09:41
 */
@Controller
public class SwaggerConfig {

    /**
     * 创建 swagger api
     *
     * @author ming
     * @date 2018-04-27 09:42
     */
    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("quartz 定时器")
                        .description("ming  quartz 定时器api")
                        .version("2018")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(Start.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }


    @GetMapping("/swagger")
    public String toSwagger(){
        return "redirect:swagger/index.html";
    }
}
