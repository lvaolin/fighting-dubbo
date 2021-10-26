package com.dhy.consumer.controller;

import com.alibaba.dubbo.common.beanutil.JavaBeanDescriptor;
import com.alibaba.dubbo.common.beanutil.JavaBeanSerializeUtil;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.dhy.common.itf.FoodDto;
import com.dhy.consumer.generic.DubboGenericCall;
import com.dhy.consumer.result.DhyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
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

            Object[] parameterValues = null;

            //准备业务参数--map代替dto-
            if ("true".equals(type)) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", 1000);
                map.put("name","mianbaoaaa");
                //参数值数组（map类型）---
                parameterValues = new Object[]{map};
            }

            if ("bean".equals(type)) {
                FoodDto foodDto = new FoodDto();
                foodDto.setName("wodemianbao");
                foodDto.setPrice(2222.99);
                foodDto.setId(1000);
                foodDto.setLastUpdateTime(new Date());
                parameterValues = new Object[]{foodDto};

            }

            if ("nativejava".equals(type)) {
                FoodDto foodDto = new FoodDto();
                foodDto.setName("wodemianbao");
                foodDto.setPrice(2222.99);
                foodDto.setId(1000);
                foodDto.setLastUpdateTime(new Date());
                parameterValues = new Object[]{foodDto};
            }

            //---根据泛化调用的方式进行 参数编码
            parameterValues = getParameterValues(parameterValues,type);

            //---进行泛化调用-----------

            Object result = dubboGenericCall.invoke(interfaceName,methodName,parameterTypeNames,parameterValues,type);

            //---根据泛化调用的方式进行 结果解码
            result = decodeResult(result,type);

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

    /**
     * 根据不同泛化类型进行 结果解码
     * @param result
     * @param type
     * @return
     * @throws Exception
     */
    private Object decodeResult (Object result,String type) throws Exception {
        if ("true".equalsIgnoreCase(type)) {
            return result;
        }
        if ("bean".equalsIgnoreCase(type)) {
            JavaBeanDescriptor re = (JavaBeanDescriptor) result;
            return JavaBeanSerializeUtil.deserialize(re);
        }
        if ("nativejava".equalsIgnoreCase(type)) {
            byte[] re = (byte[]) result;
            Serialization nativejava = ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("nativejava");
            ObjectInput deserialize = nativejava.deserialize(null, new ByteArrayInputStream(re));
            return deserialize.readObject();
        }
        return null;
    }

    /**
     * 根据不同泛化类型进行 参数编码
     * @param parameterValues
     * @param type
     * @return
     * @throws IOException
     */
    private Object[] getParameterValues(Object[] parameterValues ,String type) throws IOException {
        Object[] result = new Object[parameterValues.length];
        if ("true".equalsIgnoreCase(type)) {
            return parameterValues;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            if ("bean".equals(type)) {
                result[i] = JavaBeanSerializeUtil.serialize(parameterValues[i]);
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
