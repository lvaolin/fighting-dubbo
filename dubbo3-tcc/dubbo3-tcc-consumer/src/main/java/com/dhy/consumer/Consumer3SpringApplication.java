package com.dhy.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer3SpringApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","log4j2");
        SpringApplication.run(Consumer3SpringApplication.class, args);
    }

}
