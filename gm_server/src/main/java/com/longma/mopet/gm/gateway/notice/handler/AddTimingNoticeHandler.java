package com.longma.mopet.gm.gateway.notice.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.notice.b2s.AddTimingNoticeB2S;
import com.longma.mopet.gm.gateway.notice.handler.base.TimingNoticeHandler;
import com.longma.mopet.gm.model.notice.TimingNotice;
import com.longma.mopet.gm.rabbitmq.message.NoticeChangeMessage;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:29 2018/5/10
 * @Modified By:
 */
public class AddTimingNoticeHandler extends TimingNoticeHandler {

    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        AddTimingNoticeB2S b2S = (AddTimingNoticeB2S)message;
        TimingNotice notice = new TimingNotice(b2S);
        int total = timingNoticeDao.insert(notice);
        if (total != 1) {
            send.setErrorReturn(8,"插入失败");
        } else if(b2S.getPause()==0) {
            // mq添加一条消息
            NoticeChangeMessage msg = NoticeChangeMessage.genReLoadMsg(notice.getId());
            rmqSend.sendMessageToServers(exchange,b2S.getServers(),msg);
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return AddTimingNoticeB2S.class;
    }
}
