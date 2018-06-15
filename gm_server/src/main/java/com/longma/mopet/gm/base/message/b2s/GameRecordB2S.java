package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:15 2018/5/10
 * @Modified By:
 */
public abstract class GameRecordB2S extends RecordSearchB2S {
    private int serverId;

    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            // 服务器是否存在
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
