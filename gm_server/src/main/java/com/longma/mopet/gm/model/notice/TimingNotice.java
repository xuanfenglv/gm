package com.longma.mopet.gm.model.notice;

import com.longma.mopet.gm.gateway.notice.b2s.AddTimingNoticeB2S;
import com.longma.mopet.gm.model.notice.base.BaseIntervalNotice;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:33 2018/5/10
 * @Modified By:
 */
public class TimingNotice extends BaseIntervalNotice {
    private int repeatInterval;

    public TimingNotice() {
    }
    public TimingNotice(AddTimingNoticeB2S b2S) {
        super(b2S);
        repeatInterval = b2S.getRepeatInterval();
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
}
