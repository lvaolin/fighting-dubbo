package com.dhy.provider.configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@Data
public class ConfigDubbo {

    private static String dubboScanBasePackages="com.dhy.provider";
    @Value("${dubbo.application.id}")
    private String dubboApplicationId;
    @Value("${dubbo.application.name}")
    private String dubboApplicationName;
    @Value("${dubbo.registry.address}")
    private String dubboRegistryAddress;
    @Value("${dubbo.protocol.id}")
    private String dubboProtocolId;
    @Value("${dubbo.protocol.name}")
    private String dubboProtocolName;
    @Value("${dubbo.protocol.port}")
    private String dubboProtocolPort;
    @Value("${dubbo.registry.id}")
    private String dubboRegistryId;
    @Value("${dubbo.consumer.check:false}")
    private Boolean dubboConsumerCheck;
    @Value("${dubbo.provider.filter}")
    private String dubboProviderFilter;
    @Value("${dubbo.consumer.filter}")
    private String dubboConsumerFilter;
    @Value("${dubbo.consumer.timeout:60000}")
    private Integer dubboConsumerTimeout;
    @Value("${dubbo.consumer.retries:1}")
    private Integer dubboConsumerRetries;


    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig(dubboRegistryAddress);
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("router","myRouterFactory");
//        registryConfig.setParameters(hashMap);
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setId(dubboApplicationId);
        applicationConfig.setName(dubboApplicationName);
        applicationConfig.setRegistry(registryConfig());
        return applicationConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(dubboProtocolName);
        protocolConfig.setPort(Integer.parseInt(dubboProtocolPort));
        return protocolConfig;
    }

    @Bean
    public static   AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(dubboScanBasePackages);
        return annotationBean;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(dubboConsumerCheck);
        consumerConfig.setTimeout(dubboConsumerTimeout);
        consumerConfig.setApplication(applicationConfig());
        consumerConfig.setRetries(dubboConsumerRetries);
        consumerConfig.setFilter(dubboConsumerFilter);
        return consumerConfig;
    }

}
