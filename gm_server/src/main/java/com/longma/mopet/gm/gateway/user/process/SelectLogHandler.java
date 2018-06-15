package com.longma.mopet.gm.gateway.user.process;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.process.GmRecordSearchHandler;
import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.dao.gm.OperationRecordDao;
import com.longma.mopet.gm.gateway.user.b2s.SelectLogListB2S;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:14 2018/4/26
 * @Modified By:
 */
public class SelectLogHandler extends GmRecordSearchHandler {
    @Autowired
    private OperationRecordDao recordDao;

    @Override
    protected RecordDao getRecordDao() {
        return recordDao;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SelectLogListB2S.class;
    }
}
