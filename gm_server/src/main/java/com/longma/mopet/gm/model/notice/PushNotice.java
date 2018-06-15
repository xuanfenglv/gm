package com.longma.mopet.gm.model.notice;

import com.longma.mopet.gm.gateway.notice.b2s.AddPushNoticeB2S;
import com.longma.mopet.gm.model.notice.base.BaseNotice;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:41 2018/5/10
 * @Modified By:
 */
public class PushNotice extends BaseNotice {
    public PushNotice() {
    }

    public PushNotice(AddPushNoticeB2S b2S) {
        super(b2S);
    }
}
