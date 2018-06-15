package com.longma.mopet.gm.gateway.notice.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.GmRecordSearchHandler;
import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.dao.gm.notice.TimingNoticeDao;
import com.longma.mopet.gm.gateway.notice.b2s.SearchNoticeB2S;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:07 2018/5/14
 * @Modified By:
 */
public class SearchTimingNoticeHandler extends GmRecordSearchHandler {
    @Autowired
    protected TimingNoticeDao timingNoticeDao;

    @Override
    protected RecordDao getRecordDao() {
        return timingNoticeDao;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SearchNoticeB2S.class;
    }
}
