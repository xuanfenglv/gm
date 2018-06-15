package com.longma.mopet.gm.gateway.user.process.base;

import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.dao.gm.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:24 2018/4/24
 * @Modified By:
 */
public abstract class AbstractUserHandler extends IMsgHandler {
    @Autowired
    protected UserDao userDao;

    protected boolean existUser(String name) {
        if(userDao.existName(name)==1)
            return true;
        return false;
    }
}
