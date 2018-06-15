package com.longma.mopet.gm.model.notice.base;

import com.longma.mopet.gm.base.message.b2s.AddIntervalNoticeB2S;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:35 2018/5/10
 * @Modified By:
 */
public abstract class BaseIntervalNotice extends BaseNotice {
    private long endTime;

    public BaseIntervalNotice() {
    }

    public BaseIntervalNotice(AddIntervalNoticeB2S b2S) {
        super(b2S);
        endTime = b2S.getEndTime();
    }
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
