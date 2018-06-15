package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:55 2018/5/9
 * @Modified By:
 */
public abstract class GameLogSearchB2S extends GameRecordB2S{
    private String beginTime;
    private String endTime;

    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            // 对 beginTime 和 endTime 进行正则校验，并判断合理性

        }
        return result;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
