package com.dhy.consumer.service;

import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.OrderPo;
import com.dhy.common.itf.SeataStoragePo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@DubboService
@Component
public class OrderServiceImpl implements IOrderService {
    //@DubboReference(async = true} )//针对接口中所有方法
    @DubboReference(methods ={@Method(name = "selectCount",async = true)} )//指定特定方法生效
    private ISeataStorageService seataStorageService;
    @Override
    public List<OrderPo> selectAll() {
        List<SeataStoragePo> seataStoragePos = seataStorageService.selectAll();
        return null;
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
