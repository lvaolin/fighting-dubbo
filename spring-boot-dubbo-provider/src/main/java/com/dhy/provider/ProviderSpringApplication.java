package com.dhy.provider;

import com.dhy.common.EnableMyConfig;
import com.dhy.parent.MyComponent;
import com.dhy.demo.MyConfigDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(
        //扫描指定的类
        scanBasePackageClasses = MyConfigDemo.class,
        //扫描特殊目录
        scanBasePackages = {"com.dhy.parent"}
        )
@EnableMyConfig
public class ProviderSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProviderSpringApplication.class, args);
        System.out.println("-----------");
    }

}
