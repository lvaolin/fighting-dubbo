package com.dhy.consumer.generic;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

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

    /**
     *
     * @param interfaceName
     * @param type = "true","bean","nativejava"
     * @return
     */
    public GenericService getGenericService(String interfaceName,String type){
        String key = interfaceName+"@"+type;
        ReferenceConfig<GenericService> rc = DubboCacheUtil.referenceConfigMap.get(key);
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
            //rc.setGeneric("nativejava");//参数值使用 使用nativejava方式进行序列化与反序列化，返回值也是一样
            //rc.setGeneric("bean");//参数值 使用bean方式进行序列化与反序列化，返回值也是一样
            rc.setAsync(false);
            DubboCacheUtil.referenceConfigMap.put(key,rc);
        }
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(rc);
        return genericService;
    }

    /**
     * 进行泛化调用
     * @param interfaceName
     * @param methodName
     * @param parameterTypeNames
     * @param parameterValues
     * @param type = "true","bean","nativejava"
     */
    public Object invoke(String interfaceName, String methodName, String[] parameterTypeNames, Object[] parameterValues,String type) throws Exception {
        GenericService genericService = this.getGenericService(interfaceName,type);
        if (genericService!=null) {
            Object o = genericService.$invoke(
                    methodName,
                    parameterTypeNames,
                    parameterValues
            );
           return o;
        }else{
            throw new Exception("在注册中心没有找到可用的服务提供者");
        }
    }
}
