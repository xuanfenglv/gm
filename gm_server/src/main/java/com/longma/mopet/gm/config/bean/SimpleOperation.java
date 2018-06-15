package com.longma.mopet.gm.config.bean;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:34 2018/4/25
 * @Modified By:
 */
public class SimpleOperation {
    private int msgId;
    private String name;

    public SimpleOperation(int msgId, String name) {
        this.msgId = msgId;
        this.name = name;
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
}
