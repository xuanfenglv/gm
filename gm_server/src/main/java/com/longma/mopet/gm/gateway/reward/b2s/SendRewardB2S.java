package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.CertainServerMessage;
import com.longma.mopet.gm.bean.Reward;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:13 2018/5/4
 * @Modified By:
 */
public class SendRewardB2S extends CertainServerMessage {
    private int id;
    private long rewardId;
    private String name;
    private String account;
    private int reason;
    private String desc;
    private long deadline;
    private List<Reward> content;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(this.rewardId==0|| StringUtil.isEmpty(this.name)||StringUtil.isEmpty(this.account)||StringUtil.isEmpty(this.desc)) {
            result.setErrMsg("缺少必要的参数");
        } else if(this.deadline==0) {
            result.setErrMsg("截止时间不能为空");
        } else if(this.deadline<=System.currentTimeMillis()) {
            result.setErrMsg("截止时间不能是过去时");
        } else if(this.content.size()==0) {
            result.setErrMsg("奖励不能为空");
        } else {
           // 进行奖励有效性校验
        }
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

    public List<Reward> getContent() {
        return content;
    }

    public void setContent(List<Reward> content) {
        this.content = content;
    }
}
