package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.AddAdminB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;
import com.longma.mopet.gm.util.MD5;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:46 2018/4/23
 * @Modified By:
 */
public class AddAdminHandler extends AbstractUserHandler {

    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        AddAdminB2S receive = (AddAdminB2S)message;
        User user = new User();
        user.setName(receive.getName());
        user.setPwd(MD5.md5Digest(receive.getPwd()));
        user.setPower(receive.getPower());
        user.setDeadline(-1);
        int result = userDao.createUser(user);
        if(result!=1) {
            send.setErrorReturn(8,"插入管理员失败");
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return AddAdminB2S.class;
    }
}
