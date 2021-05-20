package com.dhy.provider.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("zipkin")
@Data
public class ZipkinProperties {


    @Value("${spring.application.name}")
    private String serviceName;
    private String url;
    private Long connectTimeout;
    private Long readTimeout;
    private Float rate;


    /*getter and setter*/
}