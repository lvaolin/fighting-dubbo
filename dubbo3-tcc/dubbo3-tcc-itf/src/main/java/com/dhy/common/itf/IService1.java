package com.dhy.common.itf;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import javax.validation.constraints.NotNull;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:00 下午
 */
public interface IService1 {
    @TwoPhaseBusinessAction(name = "IService1", commitMethod = "method1Confirm", rollbackMethod = "method1Cancel")
    boolean method1Try(BusinessActionContext actionContext, @NotNull Dto1 dto1);
    boolean method1Confirm(BusinessActionContext actionContext);
    boolean method1Cancel(BusinessActionContext actionContext);
}
