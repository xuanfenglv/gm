package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.IntervalRecordSearchB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:07 2018/5/4
 * @Modified By:
 */
public class SearchRedeemCodeRecordB2S extends IntervalRecordSearchB2S {
    private long codeId;
    private String name;

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    @Override
    public String genCondition() {
        StringBuilder sb = new StringBuilder();
        if(codeId!=0) {
            sb.append(" and ").append("codeId=").append(codeId);
        } else {
            if(!StringUtil.isEmpty(name)) {
                sb.append(" and ").append("`name` like ").append("'%").append(name).append("%'");
            }
            sb.append(super.genCondition());
        }

        return null;
    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
