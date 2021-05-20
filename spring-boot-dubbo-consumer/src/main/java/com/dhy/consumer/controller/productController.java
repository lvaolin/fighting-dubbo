package com.dhy.consumer.controller;

import com.dhy.common.itf.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/selectAll")
    Object getProduct() {
        test();
        return orderService.selectAll();
    }

    @RequestMapping("/selectCount")
    Object selectCount() {
        return orderService.selectCount();
    }


    void test(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
