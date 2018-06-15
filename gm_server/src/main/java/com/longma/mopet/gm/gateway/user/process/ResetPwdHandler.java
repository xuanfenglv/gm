package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.dao.gm.UserDao;
import com.longma.mopet.gm.gateway.user.b2s.ResetPwdB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;
import com.longma.mopet.gm.gateway.user.s2b.ResetPwdS2B;
import com.longma.mopet.gm.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:10 2018/4/24
 * @Modified By:
 */
public class ResetPwdHandler extends AbstractUserHandler {
    @Autowired
    private UserDao userDao;

    @Override
    protected S2BMessage handle(B2SMessage message) {
        ResetPwdS2B send = new ResetPwdS2B();
        ResetPwdB2S receive = (ResetPwdB2S)message;
        int num = userDao.existName(receive.getName());
        if(num==0) {
            send.setErrorReturn(9,"被操作管理员不存在");
        } else {
            String initPwd = MD5.md5Digest("mopet");
            int total = userDao.updatePwd(receive.getName(),initPwd);
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return ResetPwdB2S.class;
    }
}
