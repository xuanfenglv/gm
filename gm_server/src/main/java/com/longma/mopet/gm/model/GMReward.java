package com.longma.mopet.gm.model;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.gateway.reward.b2s.SendRewardB2S;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:37 2018/5/7
 * @Modified By:
 */
public class GMReward {
    private int id;
    private long rewardId;
    private String name;
    private String account;
    private int reason;
    private String desc;
    private long deadline;
    private String content;
    private int status;
    private String operator;

    public GMReward() {
    }

    public GMReward(SendRewardB2S b2S) {
        this.rewardId = b2S.getRewardId();
        this.name = b2S.getName();
        this.account = b2S.getAccount();
        this.reason = b2S.getReason();
        this.desc = b2S.getDesc();
        this.deadline = b2S.getDeadline();
        this.content = JSON.toJSONString(b2S.getContent());
        this.setOperator(b2S.getOperator());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRewardId() {
        return rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
