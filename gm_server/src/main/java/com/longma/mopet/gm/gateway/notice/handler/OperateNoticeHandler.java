package com.longma.mopet.gm.gateway.notice.handler;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.constant.NoticeEnum;
import com.longma.mopet.gm.dao.gm.notice.LoginNoticeDao;
import com.longma.mopet.gm.dao.gm.notice.PushNoticeDao;
import com.longma.mopet.gm.dao.gm.notice.TimingNoticeDao;
import com.longma.mopet.gm.dao.gm.notice.base.NoticeDao;
import com.longma.mopet.gm.exception.NotFountException;
import com.longma.mopet.gm.gateway.notice.b2s.OperateNoticeB2S;
import com.longma.mopet.gm.model.notice.base.BaseNotice;
import com.longma.mopet.gm.rabbitmq.message.NoticeChangeMessage;
import com.longma.mopet.gm.rabbitmq.util.RmqSend;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:40 2018/5/15
 * @Modified By:
 */
public class OperateNoticeHandler extends IMsgHandler {
    @Autowired
    protected TimingNoticeDao timingNoticeDao;
    @Autowired
    protected LoginNoticeDao loginNoticeDao;
    @Autowired
    protected PushNoticeDao pushNoticeDao;
    @Autowired
    private RmqSend rmqSend;

    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        OperateNoticeB2S b2S = (OperateNoticeB2S) message;
        NoticeDao dao = getNoticeDao(b2S.getType());

        BaseNotice notice = dao.selectById(b2S.getId());
        if (notice == null) {
            send.setErrorReturn(3, "不存在id=" + b2S.getId() + "的公告");
            return send;
        }

        int total = 0;
        if (b2S.getOperate() == 1) {
            total = dao.pause(b2S.getId());
        } else if (b2S.getOperate() == 2) {
            total = dao.start(b2S.getId());
        } else if (b2S.getOperate() == 3) {
            total = dao.del(b2S.getId());
        }
        if (total != 1) {
            send.setErrorReturn(8, "访问数据库失败");
        } else {
            // mq添加一条消息
            String exchange = NoticeEnum.getExchangeNameByType(b2S.getType());
            List<Integer> servers = JSON.parseArray(notice.getServers(), Integer.class);
            NoticeChangeMessage msg = NoticeChangeMessage.genReLoadMsg(b2S.getId());
            rmqSend.sendMessageToServers(exchange, servers, msg);
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return OperateNoticeB2S.class;
    }

    private NoticeDao getNoticeDao(int type) {
        NoticeDao dao = null;
        switch (type) {
            case 1:
                dao = timingNoticeDao;
                break;
            case 2:
                dao = loginNoticeDao;
                break;
            case 3:
                dao = pushNoticeDao;
                break;
            default:
                throw new NotFountException("不存在的notice DAO");
        }

        return dao;
    }

}
