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
public class UpdatePwdB2S extends B2SMessage {
    private String oldPwd;
    private String newPwd;
    @Override
    protected void doCheck(CheckParamResult result) {
        if(StringUtil.isEmpty(oldPwd)||StringUtil.isEmpty(newPwd)) {
            result.setErrMsg("表单参数不能为空");
        } else {
            if(newPwd.length()>15) {
               result.setErrMsg("密码长度在1-15之间");
            }
        }
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
