package com.dhy.provider.service;

import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/21 12:01 下午
 */
//@DubboService(validation = "true")
@DubboService(methods = {
        @Method(name = "sayHello" ),
        @Method(name = "eat",validation = "true" )
})
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "from dubbo2 hello,"+name;
    }

    @Override
    public FoodDto eat(FoodDto foodDto) {
        foodDto.setName("面包来自dubbo2");
        foodDto.setPrice(9999.0);
        return foodDto;
    }
}
