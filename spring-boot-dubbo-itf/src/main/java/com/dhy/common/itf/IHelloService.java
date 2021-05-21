package com.dhy.common.itf;

import javax.validation.constraints.NotNull;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:00 下午
 */
public interface IHelloService {

    String sayHello(@NotNull String name);
    FoodDto eat(@NotNull FoodDto foodDto);
}
