package com.dhy.consumer.controller;

import com.dhy.consumer.generic.DubboGenericCall;
import com.dhy.consumer.result.DhyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 泛化调用测试-----
 */
@RestController
@RequestMapping("/generic")
public class GenericController {
    @Autowired
    DubboGenericCall dubboGenericCall;

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
            //接口名称---
            String interfaceName = "com.dhy.common.itf.IHelloService";
            //方法名称---
            String methodName = "eat";
            //参数类型名称数组---
            String[] parameterTypeNames = {"com.dhy.common.itf.FoodDto"};
            //准备业务参数---
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1000);
            map.put("name","mianbaoaaa");
            //参数值数组（map类型）---
            Object[] parameterValues = new Object[]{map};
            //-----进行泛化调用-----------
            Object result = dubboGenericCall.invoke(interfaceName,methodName,parameterTypeNames,parameterValues);
            dhyResultBody.setData(result);
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
