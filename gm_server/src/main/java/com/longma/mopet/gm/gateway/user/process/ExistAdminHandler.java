package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.gm.UserDao;
import com.longma.mopet.gm.gateway.user.b2s.ExistAdminB2S;
import com.longma.mopet.gm.gateway.user.s2b.ExistAdminS2B;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:11 2018/4/23
 * @Modified By:
 */
public class ExistAdminHandler extends IMsgHandler {
    @Autowired
    private UserDao userDao;

    @Override
    protected S2BMessage handle(B2SMessage message) {
        ExistAdminS2B send = new ExistAdminS2B();
        ExistAdminB2S receive = (ExistAdminB2S)message;
        int total = userDao.existName(receive.getName());
        if(total>0){
            send.setExist(true);
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return ExistAdminB2S.class;
    }
}
