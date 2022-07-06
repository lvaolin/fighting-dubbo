package com.dhy.consumer.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/14 4:29 下午
 */
@Slf4j
public class MyConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();
        RpcContext.getContext().setAttachment("common-header","{sessionid:11}");
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException()) {
                //是否有异常，异常处理
            }
            log.info(result.getAttachment("data-ext"));
            return result;
        }finally {
            String log = invoker.getInterface().getCanonicalName()+","+invocation.getMethodName();
            System.out.println(log+","+(System.currentTimeMillis()-startTime));
        }

    }
}
