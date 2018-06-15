package com.longma.mopet.gm.base.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:32 2018/6/5
 * @Modified By:
 */
public abstract class OnlyMsgIdHandler extends IMsgHandler {

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return null;
    }
}
