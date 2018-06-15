package com.longma.mopet.gm.dao.log;

import com.longma.mopet.gm.dao.log.base.BaseLogDao;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:51 2018/5/9
 * @Modified By:
 */
@Component
public class MoneyLogDao extends BaseLogDao {
    @Override
    protected String getTableName() {
        return "money_log";
    }
}
