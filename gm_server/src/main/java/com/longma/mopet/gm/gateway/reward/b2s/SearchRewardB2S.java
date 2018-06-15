package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.GMRecordSearchB2S;
import com.longma.mopet.gm.constant.GMRewardReason;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:54 2018/5/7
 * @Modified By:
 */
public class SearchRewardB2S extends GMRecordSearchB2S {
   
    private long rewardId;
    private String name;
    private int reason;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(!GMRewardReason.constains(reason)&&reason!=0) {
            result.setErrMsg("不存在的reason");
        }
    }

    @Override
    public String genCondition() {
        return null;
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

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }
}
