package com.longma.mopet.gm.gateway.reward.handler.base;

import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.gm.GMRewardDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:46 2018/5/7
 * @Modified By:
 */
public abstract class GMRewardHandler extends IMsgHandler {
    @Autowired
    protected GMRewardDao gmRewardDao;
}
