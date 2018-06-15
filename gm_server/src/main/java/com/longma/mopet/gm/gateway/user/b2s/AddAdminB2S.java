package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:03 2018/4/23
 * @Modified By:
 */
public class AddAdminB2S extends B2SMessage {
    private String name;
    private String pwd;
    private int power;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(StringUtil.isEmpty(name)||StringUtil.isEmpty(pwd)) {
            result.setErrMsg("缺少参数");
        } else if(name.length()>10) {
            result.setErrMsg("名字长度不超过10");
        } else if(pwd.length()>10) {
            result.setErrMsg("密码长度不超过15");
        } else if(power>9||power<1) {
            result.setErrMsg("权限在1-9之间");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
