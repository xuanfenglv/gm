package com.longma.mopet.gm.gateway.notice.b2s;

import com.longma.mopet.gm.base.message.b2s.IntervalRecordSearchB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:29 2018/5/10
 * @Modified By:
 */
public class SearchNoticeB2S extends IntervalRecordSearchB2S {
    private String content;

    @Override
    public String genCondition() {
        StringBuilder sb = new StringBuilder();
        String timeCondition = super.genCondition();
        sb.append(timeCondition);
        if (!StringUtil.isEmpty(content)) {
            sb.append(" and content like '%").append(content).append("%'");
        }
        return sb.toString();
    }

    @Override
    protected void doCheck(CheckParamResult result) {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
