package com.longma.mopet.gm.gateway.notice.b2s;

import com.longma.mopet.gm.base.message.b2s.AddIntervalNoticeB2S;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description: 定时公告（跑马灯）
 * @Date:Create in 17:12 2018/5/10
 * @Modified By:
 */
public class AddTimingNoticeB2S extends AddIntervalNoticeB2S {
    // 跑马灯的时间间隔
    private int repeatInterval;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(repeatInterval<=0) {
            result.setErrMsg("重复间隔不得小于1s");
        } else if (getContent().length() > 100) {
            result.setErrMsg("公告内容长度不超过100");
        }
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
}
