package com.dhy.consumer.controller;

import com.dhy.common.itf.Dto1;
import com.dhy.common.itf.Dto2;
import com.dhy.common.itf.IService1;
import com.dhy.common.itf.IService2;
import com.dhy.consumer.result.DhyResult;
import com.dhy.consumer.service.ILocalService1;
import com.dhy.consumer.service.TccTransactionService;
import io.seata.common.util.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class Controller1 {

    
    @Autowired
    private TccTransactionService tccTransactionService;

    @RequestMapping("/biz1")
    DhyResult biz1(HttpServletRequest request) {
        String name = request.getParameter("name");
        DhyResult.DhyResultHead dhyResultHead = new DhyResult.DhyResultHead()
                .setCode("0")
                .setMsg("success")
                .setLogId("1000001")
                .setBeginTime(System.currentTimeMillis());
        DhyResult dhyResult = new DhyResult();
        DhyResult.DhyResultBody dhyResultBody = new DhyResult.DhyResultBody();
        try {
            String txId = tccTransactionService.doTransactionCommit();
            System.out.println(txId);
            Assert.isTrue(StringUtils.isNotBlank(txId), "事务开启失败");
            System.out.println("transaction commit demo finish.");

            dhyResultBody.setData("ok");
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
