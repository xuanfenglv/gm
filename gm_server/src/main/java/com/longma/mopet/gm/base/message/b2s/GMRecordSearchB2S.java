package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:00 2018/5/9
 * @Modified By:
 */
@Deprecated
public abstract class GMRecordSearchB2S extends RecordSearchB2S {
    protected long beginTime;
    protected long endTime;

    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            if (beginTime > endTime&&endTime!=0) {
                result.setErrMsg("开始时间不得小于结束时间");
            }
        }
        return result;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
