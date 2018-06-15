package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.GmRecordSearchHandler;
import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.dao.gm.RedeemCodeRecordDao;
import com.longma.mopet.gm.gateway.reward.b2s.SearchRedeemCodeRecordB2S;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:06 2018/5/4
 * @Modified By:
 */
public class SearchRedeemCodeRecordHandler extends GmRecordSearchHandler {
    @Autowired
    private RedeemCodeRecordDao redeemCodeRecordDao;
    @Override
    protected RecordDao getRecordDao() {
        return redeemCodeRecordDao;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SearchRedeemCodeRecordB2S.class;
    }
}
