package com.longma.mopet.gm.gateway.notice.handler.base;

import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.rabbitmq.util.RmqSend;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:33 2018/5/18
 * @Modified By:
 */
public abstract class NoticeHandler extends IMsgHandler {
    @Autowired
    protected RmqSend rmqSend;
}
