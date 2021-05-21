package com.dhy.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhy.common.itf.FoodDto;
import com.dhy.common.itf.IHelloService;
import com.dhy.common.itf.IOrderService;
import com.dhy.consumer.result.DhyResult;
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

    @DubboReference(validation = "false")
    private IHelloService helloService;

    @RequestMapping("/sayHello")
    DhyResult sayHello(HttpServletRequest request) {
        String name = request.getParameter("name");
        DhyResult.DhyResultHead dhyResultHead = new DhyResult.DhyResultHead()
                .setCode("0")
                .setMsg("success")
                .setLogId("1000001")
                .setBeginTime(System.currentTimeMillis());
        DhyResult dhyResult = new DhyResult();
        DhyResult.DhyResultBody dhyResultBody = new DhyResult.DhyResultBody();
        try {
            dhyResultBody.setData(helloService.sayHello(name));
        }catch (Throwable t){
            dhyResultHead.setCode("120");
            dhyResultHead.setMsg(t.getMessage());
        }
        dhyResultHead.setEndTime(System.currentTimeMillis());
        dhyResult.setHead(dhyResultHead);
        dhyResult.setBody(dhyResultBody);

        return dhyResult;
    }

    @RequestMapping("/eat")
    DhyResult eat() {
        DhyResult.DhyResultHead dhyResultHead = new DhyResult.DhyResultHead()
                .setCode("0")
                .setMsg("success")
                .setLogId("1000001")
                .setBeginTime(System.currentTimeMillis());
        DhyResult dhyResult = new DhyResult();
        DhyResult.DhyResultBody dhyResultBody = new DhyResult.DhyResultBody();
        try {
            FoodDto foodDto = new FoodDto();
            foodDto.setId(1000);
            foodDto.setName("mianbaoaa");
            dhyResultBody.setData(helloService.eat(foodDto));
        }catch (Throwable t){
            dhyResultHead.setCode("120");
            dhyResultHead.setMsg(t.getMessage());
        }
        dhyResultHead.setEndTime(System.currentTimeMillis());
        dhyResult.setHead(dhyResultHead);
        dhyResult.setBody(dhyResultBody);
        return dhyResult;
    }


}
