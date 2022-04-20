package com.dhy.provider.service;

import com.dhy.common.itf.Dto2;
import com.dhy.common.itf.IService1;
import com.dhy.common.itf.IService2;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:01 下午
 */
@DubboService
@Component
public class Service2Impl implements IService2 {
    @Override
    public Dto2 method1Try(@NotNull Dto2 dto2) {
        dto2.setName("Try ok");
        System.out.println("IService2.method1Try");
        return dto2;
    }

    @Override
    public Dto2 method1Confirm(@NotNull Dto2 dto2) {
        dto2.setName("Confirm ok");
        System.out.println("IService2.method1Confirm");
        return dto2;
    }

    @Override
    public Dto2 method1Cancel(@NotNull Dto2 dto2) {
        dto2.setName("Cancel ok");
        System.out.println("IService2.method1Cancel");
        return dto2;
    }
}
