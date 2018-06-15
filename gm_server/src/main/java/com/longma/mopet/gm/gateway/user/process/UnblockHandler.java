package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.UnblockB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;

/**
 * @Author:Lvxingqing
 * @Description: 解封
 * @Date:Create in 13:55 2018/4/25
 * @Modified By:
 */
public class UnblockHandler extends AbstractUserHandler{
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        UnblockB2S receive = (UnblockB2S)message;
        User user = userDao.selectByName(receive.getName());
        if(user==null) {
            send.setErrorReturn(9,"被操作管理员不存在");
        } else {
            if (user.getDeadline() < System.currentTimeMillis()) {
                send.setErrorReturn(9,"此管理员未被封号");
            } else {
                userDao.updateDeadline(receive.getName(),-1l);
            }
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return UnblockB2S.class;
    }
}
