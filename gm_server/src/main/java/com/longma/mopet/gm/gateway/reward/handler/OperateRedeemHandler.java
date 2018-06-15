package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.reward.b2s.OperateRedeemB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.RedeemCodeHandler;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:11 2018/5/15
 * @Modified By:
 */
public class OperateRedeemHandler extends RedeemCodeHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        OperateRedeemB2S b2S = (OperateRedeemB2S)message;
        int total = 0;
        if(b2S.getOperate()==1) {
            total = redeemCodeRecordDao.pause(b2S.getCodeId());
        } else if(b2S.getOperate()==2) {
            total = redeemCodeRecordDao.start(b2S.getCodeId());
        } else if(b2S.getOperate()==3) {
            total = redeemCodeRecordDao.delete(b2S.getCodeId());
        }
        if (total != 1) {
            send.setErrorReturn(3,"不存在的兑换码");
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return OperateRedeemB2S.class;
    }
}
