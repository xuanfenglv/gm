package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:26 2018/4/18
 * @Modified By:
 */
public class LoginB2S extends B2SMessage {

    private String name;
    private String pwd;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(StringUtil.isEmpty(this.name)||StringUtil.isEmpty(this.pwd)) {
            result.setErrMsg("用户名密码不能为空！");
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
}
