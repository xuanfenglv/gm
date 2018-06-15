package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.reward.b2s.SendRewardB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.GMRewardHandler;
import com.longma.mopet.gm.model.GMReward;
import com.longma.mopet.gm.rabbitmq.util.RmqSend;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:13 2018/5/4
 * @Modified By:
 */
public class SendRewardHandler extends GMRewardHandler {

    @Autowired
    protected RmqSend rmqSend;

    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        SendRewardB2S b2S = (SendRewardB2S)message;
        // 发送到 RabbitMq 消息队列中，由游戏服自主消费

        // gm 数据库存档
        GMReward gmReward = new GMReward(b2S);
        // 未领取
        gmReward.setStatus(1);
        int total = gmRewardDao.insert(gmReward);
        if(total!=1) {
            send.setErrorReturn(9,"奖励无法插入数据库");
        } else {
            rmqSend.sendPlainText("reward",String.valueOf(b2S.getServerId()),String.valueOf(b2S.getRewardId()));
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SendRewardB2S.class;
    }
}
