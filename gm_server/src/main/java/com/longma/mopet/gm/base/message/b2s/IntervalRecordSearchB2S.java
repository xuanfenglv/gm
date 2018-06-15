package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description: 按照有效期查询记录
 * @Date:Create in 17:51 2018/5/14
 * @Modified By:
 */
public abstract class IntervalRecordSearchB2S extends RecordSearchB2S {
    private long beginTime;
    private long endTime;

    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            if (beginTime > endTime && endTime != 0) {
                result.setErrMsg("开始时间不得大于结束时间");
            }
        }
        return result;
    }
    public String genCondition() {
        StringBuilder sb = new StringBuilder();
        if (beginTime != 0) {
            sb.append(" and beginTime>=").append(beginTime);
        }
        if (endTime != 0) {
            sb.append(" and endTime<=").append(endTime);
        }
        return sb.toString();
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
