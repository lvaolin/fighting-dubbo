package com.dhy.consumer.generic;


import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DubboCacheUtil {
    public static final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new ConcurrentHashMap<>();
}
