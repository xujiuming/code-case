package com.ming.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 *config server 拉取的配置
 *@author ming
 *@date 2017-10-30 11:17
 */
@Component
@ConfigurationProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    private String from;
}
