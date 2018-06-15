package com.longma.mopet.gm.base.message.b2s.base;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description: 浏览器发往服务器的消息(子类必须有getter、setter方法，用于json反序列化)
 * @Date:Create in 18:27 2018/4/18
 * @Modified By:
 */
public abstract class B2SMessage {
    private int msgId;
    // 操作者
    private String operator;

    public CheckParamResult checkParams() {
        CheckParamResult result = new CheckParamResult();
        doCheck(result);
        return result;
    }
    protected abstract void doCheck(CheckParamResult result);

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
