package com.longma.mopet.gm.gateway.worldmanage.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.GameRecordSearchHandler;
import com.longma.mopet.gm.dao.game.RoleInfoDao;
import com.longma.mopet.gm.dao.game.base.BaseGameDao;
import com.longma.mopet.gm.gateway.worldmanage.b2s.RoleInfoSearchB2S;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:36 2018/5/22
 * @Modified By:
 */
public class RoleInfoSearchHandler extends GameRecordSearchHandler {
    @Autowired
    private RoleInfoDao dao;

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return RoleInfoSearchB2S.class;
    }

    @Override
    protected BaseGameDao getRecordDao() {
        return dao;
    }
}
