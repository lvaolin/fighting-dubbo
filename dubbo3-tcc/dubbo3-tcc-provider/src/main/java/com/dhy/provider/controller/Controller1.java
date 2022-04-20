package com.dhy.provider.controller;

import com.dhy.common.itf.Dto1;
import com.dhy.common.itf.IService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcc")
public class Controller1 {

    @Autowired
    private IService1 service1;

    @RequestMapping("/service1/method1")
    Object service1method1() {
        return service1.method1Try(new Dto1());
    }

}
