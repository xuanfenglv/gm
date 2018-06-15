package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:21 2018/5/10
 * @Modified By:
 */
public abstract class AddIntervalNoticeB2S extends AddNoticeB2S {
    private long endTime;
    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if (result.isValid()) {
            //
            if(getBeginTime()>=endTime) {
                result.setErrMsg("有效期不对");
            }
        }
        return result;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
