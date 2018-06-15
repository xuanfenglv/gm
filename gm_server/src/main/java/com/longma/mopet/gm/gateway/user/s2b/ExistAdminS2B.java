package com.longma.mopet.gm.gateway.user.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:10 2018/4/23
 * @Modified By:
 */
public class ExistAdminS2B extends S2BMessage {
    // 管理员是否存在
    private boolean exist;

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
