package com.longma.mopet.gm.base.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.b2s.GameRecordB2S;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.game.base.BaseGameDao;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:12 2018/5/14
 * @Modified By:
 */
public abstract class GameRecordSearchHandler extends IMsgHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        RecordSearchS2B send = new RecordSearchS2B();
        GameRecordB2S b2S = (GameRecordB2S)message;
        String condition = b2S.genCondition();
        String limit = b2S.genLimit();
        int total = getRecordDao().searchTotal(b2S.getServerId(), condition);
        List list = getRecordDao().search(b2S.getServerId(),condition,limit);

        send.setTotal(total);
        send.setRecordList(list);
        return send;
    }

    protected abstract BaseGameDao getRecordDao();


}
