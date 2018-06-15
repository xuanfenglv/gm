package com.longma.mopet.gm.gateway.notice.handler.base;

import com.longma.mopet.gm.base.message.b2s.AddNoticeB2S;
import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.model.notice.base.BaseNotice;
import com.longma.mopet.gm.rabbitmq.message.NoticeChangeMessage;

/**
 * @Author:Lvxingqing
 * @Description: 添加公告处理器的基类
 * @Date:Create in 10:57 2018/5/18
 * @Modified By:
 */
public abstract class AddNoticeHandler extends NoticeHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        AddNoticeB2S b2S = (AddNoticeB2S)message;
        BaseNotice notice = getModel(b2S);

        int total = getDao().insert(notice);

        if (total != 1) {
            send.setErrorReturn(8,"插入失败");
        } else if(b2S.getPause()==0) {
            // mq添加一条消息
            NoticeChangeMessage msg = NoticeChangeMessage.genReLoadMsg(notice.getId());
            rmqSend.sendMessageToServers(getExchange(),b2S.getServers(),msg);
        }
        return send;
    }

    protected abstract NoticeDao getDao();
    protected abstract String getExchange();
    protected abstract BaseNotice getModel(AddNoticeB2S b2S);
}
