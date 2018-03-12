package com.ming.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目设置  前缀以 app开头
 *
 * @author ming
 * @date 2018-01-25 09:23
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationConfig {
}
