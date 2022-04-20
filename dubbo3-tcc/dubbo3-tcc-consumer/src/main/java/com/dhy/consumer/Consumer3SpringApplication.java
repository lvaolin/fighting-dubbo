package com.dhy.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Consumer3SpringApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","log4j2");
        //System.setProperty(CommonConstants.ENABLE_NATIVE_JAVA_GENERIC_SERIALIZE,"true");
        SpringApplication.run(Consumer3SpringApplication.class, args);
    }

}
