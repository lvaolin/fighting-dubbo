package com.dhy.consumer.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author wangping
 * @version V1.0
 * @Title: problem
 * @Package cn.gone.utils
 * @Description: 功能描述
 * @date 2021-03-19 15:39
 */
public class HttpUtil {
    private static RestTemplate restTemplate = new RestTemplate();

    static {
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }



    public static void main(String[] args){
        List<Long> list = Arrays.asList(247758307784768L,262596591102336L);
        //List<Long> list = Arrays.asList(247249563824512L);
        List<String> refList= new ArrayList<>();
        int i = 0;
        for (Long aLong : list) {
            //最外层的对象
            Map outermost = new LinkedHashMap();
            outermost.put("orgId", aLong);
            outermost.put("key", "gone-2020-03-27");

//            String url = "http://xdz.jchl.com/v1/gl/accountPeriodBegin/reCalcAccountAmount";
            // String url = "http://test.aierp.cn:8089/v1/gl/reCreateCashFlow";
            String url = "http://172.16.30.210:8008/v1/gl/imp/reUpgradeForPeriodBegin";
            JSONObject requestBody = JSONObject.parseObject(JSON.toJSONString(outermost));
            HashMap<String, String> head = new HashMap<>();
            head.put("token", "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJbNDY1NDE0MDk2MTUwNDI1NiwyNjI1OTY1OTExMDIzMzYsXCIxMDUyMTU4NTI2OTc2Njk2OFwiLDEwMCxcImRiXzEwX2tleV9zcHJpbmdcIiwxMDAwMTAwMCxudWxsLFwiMFwiLG51bGxdIiwiZXhwIjoxNTg1NzA2Njc3LCJpYXQiOjE1ODUyNzQ2Nzd9.bQmoIqkBl-M86TEaHxM5jyFQdUljNaoaxRcgcP7QNc663PEe_6thW5N9KNfPKLuBWX0rDqxOdS_m1l0LwZxK4g");

            try {
                JSONObject postRet = HttpUtil.post(url, requestBody, head);
                refList.add(String.valueOf(aLong) + JSONObject.toJSONString(postRet));
                System.out.println(String.format("%s/%s", ++i, list.size()));
                System.out.println(aLong);
                System.out.println(JSONObject.toJSONString(postRet));
            }catch (Exception ex){
                System.out.println(String.format("%s/%s", ++i, list.size()));
                System.out.println(aLong);
                System.out.println(JSONObject.toJSONString(ex));
            }
        }

    }
    /**
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

    /**
     * 发送 post 请求，返回 json对象
     *
     * @param url
     * @param requestBody body数据
     * @return JSONObject
     */
    public static JSONObject post(String url, JSONObject requestBody, Map<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (map != null) {
            headers.setAll(map);
        }
        String bodyJsonString = requestBody.toJSONString();
        HttpEntity<String> entity = new HttpEntity<>(bodyJsonString, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        String res_body = responseEntity.getBody();
        JSONObject res_body_jsonobj = JSONObject.parseObject(res_body);
        return res_body_jsonobj;
    }


    /**
     * 业务数据签名
     *
     * @param url
     * @param access_token
     * @param appSecret
     * @param requestBodyData
     * @param header
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static JSONObject postRestfulRequest(String url, String access_token, String appSecret, JSONObject requestBodyData, Map<String, String> header) throws NoSuchAlgorithmException {

        if (appSecret == null || url == null) {
            return null;
        }
        if (url == null || "".equals(url.trim())) {
            System.out.println("缺少url地址");
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        //签名准备
        long timestamp = System.currentTimeMillis();
        String jsonbody = requestBodyData.toJSONString();
        //签名前
        String before = "access_token=" + access_token + "&timestamp=" + timestamp + "&" + jsonbody + "{" + appSecret + "}";
        //签名后
        String sign = MD5(before).toUpperCase();

        //url中参数串
        String uriParameters = "?access_token=" + access_token + "&timestamp=" + timestamp + "&sign=" + sign;

        //发送请求
        JSONObject resJSONObj = HttpUtil.post(url + uriParameters, requestBodyData, header);

        //得到结果
        return resJSONObj;
    }


    /**
     * 计算MD5值
     *
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
