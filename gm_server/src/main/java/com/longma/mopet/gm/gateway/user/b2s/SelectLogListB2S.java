package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.RecordSearchB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:09 2018/4/26
 * @Modified By:
 */
public class SelectLogListB2S extends RecordSearchB2S {
    private int operationId;
    private String searchOperator;
    private long beginTime;
    private long endTime;

    @Override
    protected void doCheck(CheckParamResult result) {
        if (beginTime > endTime && endTime != 0) {
            result.setErrMsg("开始时间不得大于结束时间");
        }
    }

    @Override
    public String genCondition() {
        StringBuilder conditionSb = new StringBuilder();

        if (!StringUtil.isEmpty(getSearchOperator())) {
            setSearchOperator("'%" + getSearchOperator() + "%'");
            conditionSb.append(" and operator like ").append(getSearchOperator());
        }
        if (getOperationId() != -1) {
            conditionSb.append(" and msgId = ").append(getOperationId());
        }
        if (beginTime != 0) {
            conditionSb.append(" and time>=").append(beginTime);
        }
        if (endTime != 0) {
            conditionSb.append(" and time<=").append(endTime);
        }
        return conditionSb.toString();
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public String getSearchOperator() {
        return searchOperator;
    }

    public void setSearchOperator(String searchOperator) {
        this.searchOperator = searchOperator;
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
