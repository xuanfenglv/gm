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
 * @Date:Create in 15:39 2018/5/15
 * @Modified By:
 */
public class UpdateTimingNoticeHandler extends TimingNoticeHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        AddTimingNoticeB2S b2S = (AddTimingNoticeB2S)message;
        TimingNotice notice = new TimingNotice(b2S);
        int total = timingNoticeDao.update(notice);
        if(total!=1) {
            send.setErrorReturn(8,"访问数据库失败");
        } else {
            // mq添加一条消息
            NoticeChangeMessage msg = NoticeChangeMessage.genReLoadMsg(b2S.getId());
            rmqSend.sendMessageToServers(exchange,b2S.getServers(),msg);
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return AddTimingNoticeB2S.class;
    }
}
