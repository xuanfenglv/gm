package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.config.OperationConfig;
import com.longma.mopet.gm.gateway.user.b2s.SelectOperationListB2S;
import com.longma.mopet.gm.gateway.user.s2b.SelectOperationListS2B;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:47 2018/4/25
 * @Modified By:
 */
public class SelectOperationListHandler extends IMsgHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        SelectOperationListS2B send = new SelectOperationListS2B();
        send.setOperations(OperationConfig.getOperationList());
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SelectOperationListB2S.class;
    }
}
