package com.longma.mopet.gm.dao.game;

import com.longma.mopet.gm.dao.game.base.BaseGameDao;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 12:02 2018/5/10
 * @Modified By:
 */
@Component
public class RoleInfoDao extends BaseGameDao {
    @Override
    protected String getTableName() {
        return "role_info";
    }
}
