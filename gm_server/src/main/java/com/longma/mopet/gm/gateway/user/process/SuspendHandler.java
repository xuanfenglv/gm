package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.SuspendB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;
import com.longma.mopet.gm.manager.SessionManager;

/**
 * @Author:Lvxingqing
 * @Description: 封号处理器
 * @Date:Create in 11:05 2018/4/19
 * @Modified By:
 */
public class SuspendHandler extends AbstractUserHandler {

    private Class<? extends B2SMessage> messageType = SuspendB2S.class;

    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();

        SuspendB2S receive = (SuspendB2S)message;
        boolean exist = existUser(receive.getName());
        if(!exist) {
            send.setErrorReturn(9,"被操作管理员不存在");
        } else {
            User user = SessionManager.getUser(receive.getName());
            // 如果管理员在线，则在Session管理器中设置封号
            if(user!=null) {
                user.setDeadline(receive.getDeadline());
            }
            // 修改数据库封号时间（必须）
            userDao.updateDeadline(receive.getName(),receive.getDeadline());
        }

        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return messageType;
    }
}
