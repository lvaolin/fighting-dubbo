package com.dhy.provider.configuration.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class DubboConfig {

    @Value("${dubbo.registry.address}")
    private String registryAddress;
    @Value("${dubbo.protocol.port}")
    private Integer protocolPort;
    @Value("${dubbo.consumer.timeout}")
    private Integer consumerTimeout;
    @Value("${dubbo.consumer.retries}")
    private Integer consumerRetries;
    @Value("${dubbo.consumer.filter}")
    private String dubboConsumerFilter;





}
