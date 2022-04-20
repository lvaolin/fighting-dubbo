package com.dhy.provider.service;

import com.dhy.common.itf.Dto1;
import com.dhy.common.itf.IService1;
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
public class Service1Impl implements IService1 {
    @Override
    public Dto1 method1Try(@NotNull Dto1 dto1) {
        dto1.setName("Try ok");
        System.out.println("IService1.method1Try");
        return dto1;
    }

    @Override
    public Dto1 method1Confirm(@NotNull Dto1 dto1) {
        dto1.setName("Confirm ok");
        System.out.println("IService1.method1Confirm");
        return dto1;
    }

    @Override
    public Dto1 method1Cancel(@NotNull Dto1 dto1) {
        dto1.setName("Cancel ok");
        System.out.println("IService1.method1Cancel");
        return dto1;
    }
}
