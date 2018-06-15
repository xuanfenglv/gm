package com.longma.mopet.gm.config.bean;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 9:58 2018/4/25
 * @Modified By:
 */
public class Operation {
    // 消息号
    private int msgId;
    // 操作名
    private String name;
    // 权限
    private int power;
    // 是否进入GM操作记录日志
    private boolean needLog;

    public Operation() {
    }

    public Operation(int msgId, String name, int power, boolean isLog) {
        this.msgId = msgId;
        this.name = name;
        this.power = power;
        this.needLog = isLog;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isNeedLog() {
        return needLog;
    }

    public void setNeedLog(boolean needLog) {
        this.needLog = needLog;
    }
}
