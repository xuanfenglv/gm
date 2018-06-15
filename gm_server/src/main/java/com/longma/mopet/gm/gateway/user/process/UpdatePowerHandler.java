package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.user.b2s.UpdatePowerB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:22 2018/4/24
 * @Modified By:
 */
public class UpdatePowerHandler extends AbstractUserHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        UpdatePowerB2S receive = (UpdatePowerB2S)message;
        int sum = userDao.existName(receive.getName());
        if(sum!=1) {
            send.setErrorReturn(9,"此管理员不存在");
        } else {
            userDao.updatePower(receive.getName(), receive.getPower());
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return UpdatePowerB2S.class;
    }
}
