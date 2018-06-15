package com.longma.mopet.gm.base.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.b2s.RecordSearchB2S;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.base.RecordDao;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:12 2018/5/14
 * @Modified By:
 */
public abstract class GmRecordSearchHandler extends IMsgHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        RecordSearchS2B send = new RecordSearchS2B();
        RecordSearchB2S b2S = (RecordSearchB2S)message;
        String condition = b2S.genCondition();
        String limit = b2S.genLimit();
        int total = getRecordDao().selectTotalByCondition(condition);
        List list = getRecordDao().selectListByConditionAndLimit(condition,limit);

        send.setTotal(total);
        send.setRecordList(list);
        return send;
    }

    protected abstract RecordDao getRecordDao();


}
