package com.dhy.consumer.filter;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.rpc.*;

import java.io.IOException;

/**
 * @Project spring-boot-dubbo
 * @Description 消费者端过滤器
 * @Author lvaolin
 * @Date 2021/5/14 4:29 下午
 */
public class MyConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startTime = System.currentTimeMillis();
        try {
            Result result = invoker.invoke(invocation);
            if (result.hasException()) {
                //是否有异常，异常处理
            }
            return result;
        }catch (Throwable e){
            //e.printStackTrace();
            dubboRpcLog(invoker,invocation,e.getMessage());
            throw e;
        }finally {
            String log = invoker.getInterface().getCanonicalName()+","+invocation.getMethodName();
            System.out.println(log+","+(System.currentTimeMillis()-startTime));
        }

    }


    /**
     * 记录调用日志
     * @param invoker
     * @param invocation
     */
    private void dubboRpcLog(Invoker<?> invoker, Invocation invocation,String errorMsg) {

        StringBuilder message = new StringBuilder();
        message.append("dubboUrl:").append(invoker.getUrl()).append(";");
        message.append("interface:").append(invoker.getInterface().getName()).append(";");
        message.append("methodName:").append(invocation.getMethodName()).append(";");
        //message.append("tokenStr:").append(RpcContext.getContext().getAttachment(CommonConst.tokenStr)).append(";");
        //message.append("DbKey:").append(RpcContext.getContext().getAttachment(CommonConst.dbKey)).append(";");
        //message.append("orgId:").append(RpcContext.getContext().getAttachment(CommonConst.orgId)).append(";");

        Class<?>[] parameterTypes = invocation.getParameterTypes();
        Object[] arguments = invocation.getArguments();

        if (parameterTypes!=null||parameterTypes.length>0) {
            message.append("arguments:");
            try {
                message.append(JSON.json(arguments));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(message.toString());
    }
}
