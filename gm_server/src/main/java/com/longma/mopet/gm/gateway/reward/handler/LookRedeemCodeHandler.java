package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.reward.b2s.LookRedeemCodeB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.RedeemCodeHandler;
import com.longma.mopet.gm.gateway.reward.s2b.LookRedeemCodeS2B;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:24 2018/5/4
 * @Modified By:
 */
public class LookRedeemCodeHandler extends RedeemCodeHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        LookRedeemCodeS2B s2B = new LookRedeemCodeS2B();
        LookRedeemCodeB2S b2S = (LookRedeemCodeB2S)message;
        List<String> codeList = redeemCodeDao.selectRedeemCodeByCodeId(b2S.getCodeId());
        s2B.setCodeList(codeList);
        return s2B;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return LookRedeemCodeB2S.class;
    }
}
