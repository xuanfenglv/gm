package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.reward.b2s.UpdateRedeemB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.RedeemCodeHandler;
import com.longma.mopet.gm.model.RedeemCodeRecord;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:36 2018/5/16
 * @Modified By:
 */
public class UpdateRedeemHandler extends RedeemCodeHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        UpdateRedeemB2S b2S = (UpdateRedeemB2S)message;
        RedeemCodeRecord record = new RedeemCodeRecord(b2S);
        int total = redeemCodeRecordDao.update(record);
        if(total!=1) {
            send.setErrorReturn(8,"修改失败");
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return UpdateRedeemB2S.class;
    }
}
