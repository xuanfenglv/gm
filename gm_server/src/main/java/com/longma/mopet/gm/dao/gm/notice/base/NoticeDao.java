package com.longma.mopet.gm.dao.gm.notice.base;

import com.longma.mopet.gm.dao.base.RecordDao;
import com.longma.mopet.gm.model.notice.base.BaseNotice;

public interface NoticeDao extends RecordDao {
    BaseNotice selectById(int id);
    int insert(BaseNotice notice);
    int pause(int id);

    int start(int id);

    int del(int id);

    int update(BaseNotice notice);
}
