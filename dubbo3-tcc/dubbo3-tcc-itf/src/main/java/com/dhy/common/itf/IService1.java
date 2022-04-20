package com.dhy.common.itf;

import javax.validation.constraints.NotNull;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:00 下午
 */
public interface IService1 {
    Dto1 method1Try(@NotNull Dto1 dto1);
    Dto1 method1Confirm(@NotNull Dto1 dto1);
    Dto1 method1Cancel(@NotNull Dto1 dto1);
}
