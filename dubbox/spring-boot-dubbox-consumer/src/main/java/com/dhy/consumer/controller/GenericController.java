package com.dhy.consumer.controller;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.dhy.common.itf.FoodDto;
import com.dhy.consumer.generic.DubboGenericCall;
import com.dhy.consumer.result.DhyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
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
    DhyResult eat(@RequestParam String type) {
        type = "nativejava";
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
//            //准备业务参数--map代替dto-
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", 1000);
//            map.put("name","mianbaoaaa");
//            //参数值数组（map类型）---
//            Object[] parameterValues = new Object[]{map};
            FoodDto foodDto = new FoodDto();
            foodDto.setName("wodemianbao");
            foodDto.setPrice(2222.99);
            foodDto.setId(1000);
            foodDto.setLastUpdateTime(new Date());
            Object[] parameterValues = new Object[]{foodDto};


            //根据泛化调用的方式进行参数转换
            parameterValues = getParameterValues(parameterValues,type);

            //-----进行泛化调用-----------
            Object result = dubboGenericCall.invoke(interfaceName,methodName,parameterTypeNames,parameterValues,type);
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

    private Object[] getParameterValues(Object[] parameterValues ,String type) throws IOException {
        Object[] result = new Object[parameterValues.length];
        if ("true".equalsIgnoreCase(type)) {
            return parameterValues;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            if ("bean".equalsIgnoreCase(type)) {

            }
            if ("nativejava".equalsIgnoreCase(type)) {
                //准备一块缓冲区
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                //获取序列化器
                Serialization nativejava = ExtensionLoader.getExtensionLoader(Serialization.class)
                        .getExtension("nativejava");
                //序列化器与缓冲区关联
                ObjectOutput output = nativejava.serialize(null, byteArrayOutputStream);
                //序列化器向缓冲区中写入byte
                output.writeObject(parameterValues[i]);
                //从缓冲区获取序列化结果
                result[i] = byteArrayOutputStream.toByteArray();
            }
        }

        return result;
    }

}
