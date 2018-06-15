package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:56 2018/4/25
 * @Modified By:
 */
public class UnblockB2S extends B2SMessage {
    private String name;
    @Override
    protected void doCheck(CheckParamResult result) {
        if (StringUtil.isEmpty(name)) {
            result.setErrMsg("找不到操作对象");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
