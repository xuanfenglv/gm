package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:19 2018/4/24
 * @Modified By:
 */
public class UpdatePowerB2S extends B2SMessage {
    private String name;
    private int power;
    @Override
    protected void doCheck(CheckParamResult result) {
        if(StringUtil.isEmpty(name)) {
            result.setErrMsg("备操作账号为空");
        } else if(power>9||power<0) {
            result.setErrMsg("权限范围错误");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
