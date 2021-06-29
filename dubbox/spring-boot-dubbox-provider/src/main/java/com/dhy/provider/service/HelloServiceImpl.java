package com.dhy.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;
import org.springframework.stereotype.Component;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:01 下午
 */
@Service
@Component
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "from dubbox hello,"+name;
    }

    @Override
    public FoodDto eat(FoodDto foodDto) {
        foodDto.setName("面包来自dubbox");
        foodDto.setPrice(9999.0);
        return foodDto;
    }
}
