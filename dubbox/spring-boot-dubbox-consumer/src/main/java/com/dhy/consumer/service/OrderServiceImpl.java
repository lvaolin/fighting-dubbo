package com.dhy.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.OrderPo;
import com.dhy.common.itf.SeataStoragePo;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private INotify notify;
    @Reference
    private ISeataStorageService seataStorageService;
    @Override
    public List<OrderPo> selectAll() {
        List<SeataStoragePo> seataStoragePos = seataStorageService.selectAll(new SeataStoragePo());
        //Future<Object> future = RpcContext.getContext().getFuture();
        //future.get();

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
