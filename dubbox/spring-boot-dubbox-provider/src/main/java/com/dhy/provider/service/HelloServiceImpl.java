package com.dhy.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:01 下午
 */
@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }

    @Override
    public FoodDto eat(FoodDto foodDto) {
        foodDto.setPrice(9999.0);
        return foodDto;
    }
}
