package com.longma.mopet.gm.gateway.reward.handler.base;

import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.gm.RedeemCodeDao;
import com.longma.mopet.gm.dao.gm.RedeemCodeRecordDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:42 2018/5/4
 * @Modified By:
 */
public abstract class RedeemCodeHandler extends IMsgHandler {
    @Autowired
    protected RedeemCodeDao redeemCodeDao;
    @Autowired
    protected RedeemCodeRecordDao redeemCodeRecordDao;

}
