package com.dhy.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProviderSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProviderSpringApplication.class, args);
        System.out.println("-----------");
    }

}
