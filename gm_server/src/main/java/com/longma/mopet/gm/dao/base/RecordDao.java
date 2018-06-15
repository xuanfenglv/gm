package com.longma.mopet.gm.dao.base;

import com.longma.mopet.gm.model.base.Record;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:24 2018/5/14
 * @Modified By:
 */
public interface RecordDao {
    int selectTotalByCondition(String condition);
    List<? extends Record> selectListByConditionAndLimit(String condition, String limit);
}
