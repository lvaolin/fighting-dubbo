package com.dhy.provider.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

/**
 * @Project spring-boot-dubbo
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/5/14 4:29 下午
 */
@Slf4j
public class MyProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();
        try {
            log.info(RpcContext.getContext().getAttachment("common-header"));
            Result result = invoker.invoke(invocation);
            if (result.hasException()) {
                //是否有异常，异常处理
            }
            result.setAttachment("data-ext","额外数据回传");
            return result;
        }finally {
            String log = invoker.getInterface().getCanonicalName()+","+invocation.getMethodName();
            System.out.println(log+","+(System.currentTimeMillis()-startTime));
        }

    }
}
