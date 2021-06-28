package com.dhy.consumer.config;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @version V1.0
 * @uathor gaoen
 * @Title: horcrux_fi
 * @Description: 描述
 * @Date 2021-05-25 10:56
 */
public class ThreadTest {

    private static ExecutorService service = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String arrs[]){
        for (int i = 0; i < 15; i++) {
            final int j = i + 1;
            System.out.println(j + " Start----====" + LocalDateTime.now());


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            thread.start();

            thread.stop();

            Future<Object> future = service.submit(getCallable(new Callable<Object>() {
                @Override
                public Object call() {
                    try {
                        // 休眠10秒
                        startWork(10, j);
                        return null;
                    } catch (Exception ex) {
                        return null;
                    }
                }
            }));
            try {
                future.get(10, TimeUnit.MILLISECONDS);
            } catch (TimeoutException ex) {
                System.out.println(j + "startWork超时");
                future.cancel(true);
            }catch (Exception ex){
            }
            System.out.println(j + " end----====" + LocalDateTime.now());
        }
    }

    static void startWork(int second, int point){
        try {
            long start = System.currentTimeMillis();
            //TimeUnit.MILLISECONDS.sleep(second * 1000);
            String url = "http://172.16.30.210:8008/v1/gl/account/query";
            //最外层的对象
            Map<String, Object> outermost = new LinkedHashMap<>();
            outermost.put("accountTypeId", "5000010001");

            JSONObject requestBody = JSONObject.parseObject(JSONObject.toJSONString(outermost));
            HashMap<String, String> head = new HashMap<>();
            head.put("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJbMzE5NDEzNzc1NzY3NTUyLDMzNjkwNTQwMDkxODI3MiwxMDYzMTYyMTkwNzY2Nzg5MSwxMDAsXCJkYl8xMl9rZXlfc3ByaW5nXCIsMTAwMDEwMDAsbnVsbCxcIjBcIixudWxsXSIsImV4cCI6MTYyMjM0NjgwOSwiaWF0IjoxNjIxOTE0ODA5fQ.h6hXInig468Dj6KMeDaX0y_f9h16UCAfk-KOR-gF7pn1JEt8M5odkW67ZJppbEYHlIFZ8QoxRLDVDznL2JTC9A");
            System.out.println(point + "HttpUtil.post----");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("----我被中断了----");
                return;
            }
            JSONObject postRet = HttpUtil.post(url, requestBody, head);
            //System.out.println(JSONObject.toJSONString(postRet));
            System.out.println(point + "startWork执行完毕"+ (System.currentTimeMillis() - start));
        } catch (Exception e) {
            System.out.println("强制终止" + e.getMessage());
        }
    }

    public static Callable<Object> getCallable(Callable<Object> task) {
        return new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return task.call();
            }
        };
    }
}
