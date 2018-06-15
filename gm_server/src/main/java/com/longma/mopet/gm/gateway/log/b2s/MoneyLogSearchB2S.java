package com.longma.mopet.gm.gateway.log.b2s;

import com.longma.mopet.gm.base.message.b2s.GameLogSearchB2S;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:43 2018/5/9
 * @Modified By:
 */
public class MoneyLogSearchB2S extends GameLogSearchB2S {
    private String roleId;
    private int currencyType;
    private int reason;

    @Override
    protected void doCheck(CheckParamResult result) {
        // 对 货币类型、原因进行合法校验（不校验也可以）
    }

    @Override
    public String genCondition() {
        return null;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }
}
