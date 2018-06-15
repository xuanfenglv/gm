package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.User;
import com.longma.mopet.gm.gateway.user.b2s.UpdatePwdB2S;
import com.longma.mopet.gm.gateway.user.process.base.AbstractUserHandler;
import com.longma.mopet.gm.util.MD5;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:52 2018/4/24
 * @Modified By:
 */
public class UpdatePwdHandler extends AbstractUserHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        UpdatePwdB2S receive = (UpdatePwdB2S)message;

        User user = userDao.selectByName(receive.getOperator());
        if(user == null) {
            send.setErrorReturn(9,"此管理员不存在");
        } else {
            String oldPwd_MD5 = MD5.md5Digest(receive.getOldPwd());
            if(!oldPwd_MD5.equals(user.getPwd())) {
                send.setErrorReturn(9,"旧密码不正确");
            } else {
                String newPwd_MD5 = MD5.md5Digest(receive.getNewPwd());
                userDao.updatePwd(receive.getOperator(),newPwd_MD5);
            }
        }

        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return UpdatePwdB2S.class;
    }
}
