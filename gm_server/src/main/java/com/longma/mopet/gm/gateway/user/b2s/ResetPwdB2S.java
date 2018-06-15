package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:11 2018/4/24
 * @Modified By:
 */
public class ResetPwdB2S extends B2SMessage {
    private String name;
    @Override
    protected void doCheck(CheckParamResult result) {
        if(StringUtil.isEmpty(name)) {
            result.setErrMsg("无操作账号");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
