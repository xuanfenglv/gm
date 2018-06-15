package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:18 2018/4/24
 * @Modified By:
 */
public class SelectUserListB2S extends B2SMessage {
    private String name;
    private int minPower;
    private int maxPower;
    @Override
    protected void doCheck(CheckParamResult result) {
        if(minPower==0||maxPower==0) {
            result.setErrMsg("缺少必要的参数");
        } else {
            if(maxPower<minPower) {
                result.setErrMsg("右侧权限不得小于左侧权限");
            } else if(minPower<1||maxPower>9) {
                result.setErrMsg("权限范围不正确（请正确使用gm）");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPower() {
        return minPower;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }
}
