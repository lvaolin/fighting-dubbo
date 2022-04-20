package com.dhy.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Provider3SpringApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","log4j2");
        ConfigurableApplicationContext run = SpringApplication.run(Provider3SpringApplication.class, args);
        System.out.println("-----------");
    }

}
