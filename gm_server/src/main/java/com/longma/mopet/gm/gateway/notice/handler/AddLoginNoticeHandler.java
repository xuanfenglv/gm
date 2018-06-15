package com.longma.mopet.gm.gateway.notice.handler;

import com.longma.mopet.gm.base.message.b2s.AddNoticeB2S;
import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.constant.NoticeEnum;
import com.longma.mopet.gm.dao.gm.notice.LoginNoticeDao;
import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.gateway.notice.b2s.AddLoginNoticeB2S;
import com.longma.mopet.gm.gateway.notice.handler.base.AddNoticeHandler;
import com.longma.mopet.gm.model.notice.LoginNotice;
import com.longma.mopet.gm.model.notice.base.BaseNotice;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:15 2018/5/18
 * @Modified By:
 */
public class AddLoginNoticeHandler extends AddNoticeHandler {
    @Autowired
    private LoginNoticeDao dao;

    private final String exchange = NoticeEnum.LOGIN.getExchange();

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
        return new LoginNotice((AddLoginNoticeB2S)b2S);
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return AddLoginNoticeB2S.class;
    }
}
