package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 19:09 2018/5/16
 * @Modified By:
 */
public abstract class CertainServerMessage extends B2SMessage {
    private int serverId;

    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if (result.isValid()) {
            // 判断服务器id是否存在
        }
        return result;
    }


    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
}
