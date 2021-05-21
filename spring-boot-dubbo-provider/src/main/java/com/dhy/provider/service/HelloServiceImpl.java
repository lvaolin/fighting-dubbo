package com.dhy.provider.service;

import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:01 下午
 */
@DubboService(validation = "true")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }

    @Override
    public String eat(FoodDto foodDto) {
        return foodDto.getName();
    }
}
