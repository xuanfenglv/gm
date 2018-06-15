package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:22 2018/5/4
 * @Modified By:
 */
public class LookRedeemCodeB2S extends B2SMessage {
    private long codeId;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(codeId==0) {
            result.setErrMsg("无codeId");
        }
    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }
}
