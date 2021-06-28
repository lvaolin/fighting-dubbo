package com.dhy.consumer.service;

import com.alibaba.fastjson.JSON;
import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.OrderPo;
import com.dhy.common.itf.SeataStoragePo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private INotify notify;
    @DubboReference(methods ={
            @Method(name = "selectCount",async = true),
            @Method(name = "selectAll",async = false
//                    ,oninvoke = "notify.oninvoke"
//                    ,onreturn ="notify.onreturn"
//                    ,onthrow ="notify.onthrow"
            ),
            @Method(name = "insert",async = true,isReturn = false,sent = false),
            @Method(name = "delete",async = true)
    })
    private ISeataStorageService seataStorageService;
    @Override
    public List<OrderPo> selectAll() {
        List<SeataStoragePo> seataStoragePos = seataStorageService.selectAll(new SeataStoragePo());
        //Future<Object> future = RpcContext.getContext().getFuture();
        //future.get();
        System.out.println(JSON.toJSONString(seataStoragePos));

        OrderPo orderPo = new OrderPo();
        orderPo.setId(1000);
        orderPo.setPrice(2000d);
        orderPo.setStock(300);
        orderPo.setLastUpdateTime(new Date());
        ArrayList<OrderPo> orderPos = new ArrayList<>();
        orderPos.add(orderPo);
        return orderPos;
    }

    public void oninvokeForSelectAll(){
        System.out.println("oninvoke");
    }

    @Override
    public int selectCount() {
        int count = seataStorageService.selectCount();
        Future<Integer> future = RpcContext.getContext().getFuture();
        try {
            count = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return count;
    }


}
