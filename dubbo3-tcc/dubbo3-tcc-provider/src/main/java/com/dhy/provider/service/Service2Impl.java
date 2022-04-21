package com.dhy.provider.service;

import com.dhy.common.itf.Dto2;
import com.dhy.common.itf.IService1;
import com.dhy.common.itf.IService2;
import io.seata.rm.tcc.api.BusinessActionContext;
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
    public boolean method1Try(BusinessActionContext actionContext, @NotNull Dto2 dto2) {
        dto2.setName("Try ok");
        System.out.println("IService2.method1Try");
        return true;
    }

    @Override
    public boolean method1Confirm(BusinessActionContext actionContext) {
        System.out.println("IService2.method1Confirm");
        return true;
    }

    @Override
    public boolean method1Cancel(BusinessActionContext actionContext) {
        System.out.println("IService2.method1Cancel");
        return true;
    }
}
