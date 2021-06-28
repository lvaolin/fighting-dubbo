package com.dhy.consumer.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project spring-boot-dubbo
 * @Description 结果包装类
 * @Author lvaolin
 * @Date 2021/5/21 2:41 下午
 */

public class DhyResult implements Serializable {
    private DhyResultHead head;
    private DhyResultBody body;

    public DhyResult setHead(DhyResultHead head) {
        this.head = head;
        return this;
    }
    public DhyResult setBody(DhyResultBody body) {
        this.body = body;
        return this;
    }

    public DhyResultHead getHead() {
        return head;
    }


    public DhyResultBody getBody() {
        return body;
    }

    public static class DhyResultHead{
        private String logId;
        private String code;
        private String msg;
        private long beginTime;
        private long endTime;

        public String getLogId() {
            return logId;
        }

        public DhyResultHead setLogId(String logId) {
            this.logId = logId;
            return this;
        }

        public String getCode() {
            return code;
        }

        public DhyResultHead setCode(String code) {
            this.code = code;
            return this;
        }

        public String getMsg() {
            return msg;
        }

        public DhyResultHead setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public DhyResultHead setBeginTime(long beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public long getEndTime() {
            return endTime;
        }

        public DhyResultHead setEndTime(long endTime) {
            this.endTime = endTime;
            return this;
        }


    }
    public static class DhyResultBody{
        public Object getData() {
            return data;
        }

        public DhyResultBody setData(Object data) {
            this.data = data;
            return this;
        }

        private Object data;

    }
}
