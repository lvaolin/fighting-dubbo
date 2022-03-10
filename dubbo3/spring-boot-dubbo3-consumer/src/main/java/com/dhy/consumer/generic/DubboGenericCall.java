package com.dhy.consumer.generic;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Project horcrux
 * @Description 泛化调用通道
 * @Author lvaolin
 * @Date 2021/1/29 5:59 下午
 */
@Component
@Slf4j
public class DubboGenericCall {

    @Value("${dubbo.registry.address}")
    private String regServer;
    @Value("${spring.application.name}")
    private String applicationName;


    public GenericService getGenericService(String interfaceName,String type){
        ReferenceConfig<GenericService> rc = DubboCacheUtil.referenceConfigMap.get(interfaceName);
        if (rc==null) {
            //服务信息
            ApplicationConfig application = new ApplicationConfig();
            application.setName(applicationName);
            //注册中心
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(regServer);
            //消费者端
            ConsumerConfig consumerConfig = new ConsumerConfig();
            if ("true".equals(type)) {
                consumerConfig.setGeneric(true);
            }
            if ("bean".equals(type)) {
                consumerConfig.setGeneric("bean");
            }
            if ("nativejava".equals(type)) {
                consumerConfig.setGeneric("nativejava");
            }

            consumerConfig.setTimeout(300000);
            consumerConfig.setRetries(0);
            //引用配置
            rc = new ReferenceConfig<GenericService>();
            rc.setConsumer(consumerConfig);
            rc.setApplication(application);
            rc.setRegistry(registry);
            rc.setInterface(interfaceName);
            //rc.setGeneric("true");//map 传参，返回值也是map
            //rc.setGeneric("nativejava");//使用nativejava方式进行序列化与反序列化，返回值也是一样
            //rc.setGeneric("bean");//使用bean方式进行序列化与反序列化，返回值也是一样
            rc.setAsync(false);
            DubboCacheUtil.referenceConfigMap.put(interfaceName,rc);
        }
        SimpleReferenceCache cache = SimpleReferenceCache.getCache();
        GenericService genericService = cache.get(rc);
        return genericService;
    }

    /**
     * 进行泛化调用
     * @param interfaceName
     * @param methodName
     * @param parameterTypeNames
     * @param parameterValues
     */
    public Object invoke(String interfaceName, String methodName, String[] parameterTypeNames, Object[] parameterValues,String type) throws Exception {
        GenericService genericService = this.getGenericService(interfaceName,type);
        if (genericService!=null) {
            return genericService.$invoke(
                    methodName,
                    parameterTypeNames,
                    parameterValues
            );
        }else{
            throw new Exception("在注册中心没有找到可用的服务提供者");
        }
    }
}
