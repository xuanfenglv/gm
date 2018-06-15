package com.longma.mopet.gm.model;


import com.longma.mopet.gm.model.base.Record;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:27 2018/4/25
 * @Modified By:
 */
public class OperationRecord implements Record {
    private int id;
    private int msgId;
    private String name;
    private String operator;
    private long time;
    private int ret;
    private String request;
    private String response;

    public OperationRecord() {
    }

    public OperationRecord(int msgId, String name, String operator, long time, int ret, String request, String response) {
        this.msgId = msgId;
        this.name = name;
        this.operator = operator;
        this.time = time;
        this.ret = ret;
        this.request = request;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
