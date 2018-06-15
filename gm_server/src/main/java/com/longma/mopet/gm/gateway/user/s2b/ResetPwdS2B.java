package com.longma.mopet.gm.gateway.user.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:11 2018/4/24
 * @Modified By:
 */
public class ResetPwdS2B extends S2BMessage {
    private boolean exist;

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isExist() {
        return exist;
    }
}
