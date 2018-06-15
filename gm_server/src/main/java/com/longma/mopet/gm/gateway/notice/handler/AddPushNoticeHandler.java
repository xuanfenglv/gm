package com.longma.mopet.gm.gateway.notice.handler;

import com.longma.mopet.gm.base.message.b2s.AddNoticeB2S;
import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.constant.NoticeEnum;
import com.longma.mopet.gm.dao.gm.notice.PushNoticeDao;
import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.gateway.notice.b2s.AddPushNoticeB2S;
import com.longma.mopet.gm.gateway.notice.handler.base.AddNoticeHandler;
import com.longma.mopet.gm.model.notice.PushNotice;
import com.longma.mopet.gm.model.notice.base.BaseNotice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:32 2018/5/18
 * @Modified By:
 */
public class AddPushNoticeHandler extends AddNoticeHandler {
    @Autowired
    private PushNoticeDao dao;

    private final String exchange = NoticeEnum.PUSH.getExchange();

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return AddPushNoticeB2S.class;
    }

    @Override
    protected NoticeDao getDao() {
        return dao;
    }

    @Override
    protected String getExchange() {
        return exchange;
    }

    @Override
    protected BaseNotice getModel(AddNoticeB2S b2S) {
        return new PushNotice((AddPushNoticeB2S) b2S);
    }
}
