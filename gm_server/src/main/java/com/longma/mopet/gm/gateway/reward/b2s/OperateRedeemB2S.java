package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:09 2018/5/15
 * @Modified By:
 */
public class OperateRedeemB2S extends B2SMessage {
    private int operate;
    private long CodeId;
    @Override
    protected void doCheck(CheckParamResult result) {
        if (operate < 1 || operate > 3) {
            result.setErrMsg("无效的操作类型");
        }
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public long getCodeId() {
        return CodeId;
    }

    public void setCodeId(long codeId) {
        CodeId = codeId;
    }
}
