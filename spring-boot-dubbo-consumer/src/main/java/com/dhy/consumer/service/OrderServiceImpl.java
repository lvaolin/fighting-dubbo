package com.dhy.consumer.service;

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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@DubboService
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
        Future<Object> future = RpcContext.getContext().getFuture();
        //future.get();
        return null;
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
