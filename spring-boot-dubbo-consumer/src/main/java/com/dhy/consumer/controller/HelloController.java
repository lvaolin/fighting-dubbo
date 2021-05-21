package com.dhy.consumer.controller;

import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;
import com.dhy.common.itf.IOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @DubboReference
    private IHelloService helloService;

    @RequestMapping("/sayHello")
    String sayHello(HttpServletRequest request) {
        String name = request.getParameter("name");
        return helloService.sayHello(name);
    }

    @RequestMapping("/eat")
    String eat(HttpServletRequest request) {
        FoodDto foodDto = new FoodDto();
        return helloService.eat(foodDto);
    }


}
