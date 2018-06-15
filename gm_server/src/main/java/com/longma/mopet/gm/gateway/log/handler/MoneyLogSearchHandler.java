package com.longma.mopet.gm.gateway.log.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.dao.log.MoneyLogDao;
import com.longma.mopet.gm.dao.log.base.BaseLogDao;
import com.longma.mopet.gm.gateway.log.b2s.MoneyLogSearchB2S;
import com.longma.mopet.gm.gateway.log.handler.base.BaseLogSearchHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:41 2018/5/9
 * @Modified By:
 */
public class MoneyLogSearchHandler extends BaseLogSearchHandler {

    @Autowired
    private MoneyLogDao moneyLogDao;

    @Override
    protected S2BMessage handle(B2SMessage message) {

        MoneyLogSearchB2S b2S = (MoneyLogSearchB2S) message;
        StringBuilder sb = new StringBuilder();
        String condition = sb.toString();
        RecordSearchS2B send = null;
        if(b2S.getType()==1) {
            send = paging(b2S,condition);
        } else {
            send = getAll(b2S,condition);
        }

        return send;
    }

    @Override
    protected BaseLogDao getLogDao() {
        return moneyLogDao;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return MoneyLogSearchB2S.class;
    }
}
