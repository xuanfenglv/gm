package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.SelectUserListB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;
import com.longma.mopet.gm.gateway.user.s2b.SelectUserListS2B;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:26 2018/4/24
 * @Modified By:
 */
public class SelectUserListHandler extends AbstractUserHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        SelectUserListS2B send = new SelectUserListS2B();
        SelectUserListB2S receive = (SelectUserListB2S)message;
        if(receive.getName()==null) {
            receive.setName("");
        }
        List<User> userList = userDao.selectUserList(receive);
        send.setUserList(userList);
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SelectUserListB2S.class;
    }
}
