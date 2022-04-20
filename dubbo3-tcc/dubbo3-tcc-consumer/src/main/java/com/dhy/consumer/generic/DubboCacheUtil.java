package com.dhy.consumer.generic;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DubboCacheUtil {
    public static final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new ConcurrentHashMap<>();
}
