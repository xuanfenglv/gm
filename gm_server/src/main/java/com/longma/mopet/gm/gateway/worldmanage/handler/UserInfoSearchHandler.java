package com.longma.mopet.gm.gateway.worldmanage.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.GameRecordSearchHandler;
import com.longma.mopet.gm.dao.game.UserInfoDao;
import com.longma.mopet.gm.dao.game.base.BaseGameDao;
import com.longma.mopet.gm.gateway.worldmanage.b2s.UserInfoSearchB2S;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:31 2018/5/10
 * @Modified By:
 */
public class UserInfoSearchHandler extends GameRecordSearchHandler {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    protected BaseGameDao getRecordDao() {
        return userInfoDao;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return UserInfoSearchB2S.class;
    }


}
