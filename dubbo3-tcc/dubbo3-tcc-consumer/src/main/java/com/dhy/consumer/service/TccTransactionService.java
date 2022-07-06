package com.dhy.consumer.service;

import com.dhy.common.itf.Dto1;
import com.dhy.common.itf.Dto2;
import com.dhy.common.itf.IService1;
import com.dhy.common.itf.IService2;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TccTransactionService {

    @Autowired
    private ILocalService1 tccActionOne;
    @DubboReference(check = false,lazy = true)
    private IService1 service1;
    @DubboReference(check = false,lazy = true)
    private IService2 service2;

    /**
     * 发起分布式事务
     *
     * @return string string
     */
    @GlobalTransactional
    public String doTransactionCommit() {
        System.out.println("XID:"+RootContext.getXID());
        //第一个TCC 事务参与者
        boolean result = service1.method1Try(null,new Dto1());
        if (!result) {
            throw new RuntimeException("TccActionOne failed.");
        }

        result = service2.method1Try(null,new Dto2());
        if (!result) {
            throw new RuntimeException("TccActionTwo failed.");
        }
        return RootContext.getXID();
    }

    /**
     * Do transaction rollback string.
     *
     * @param map the map
     * @return the string
     */
    @GlobalTransactional
    public String doTransactionRollback(Map map) {
        //第一个TCC 事务参与者
        boolean result = service1.method1Try(null,new Dto1());
        if (!result) {
            throw new RuntimeException("TccActionOne failed.");
        }
        List list = new ArrayList();
        list.add("c1");
        list.add("c2");
        result = service2.method1Try(null,new Dto2());
        if (!result) {
            throw new RuntimeException("TccActionTwo failed.");
        }
        map.put("xid", RootContext.getXID());
        throw new RuntimeException("transacton rollback");
    }

}