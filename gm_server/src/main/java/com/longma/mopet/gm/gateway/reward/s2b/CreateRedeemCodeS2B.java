package com.longma.mopet.gm.gateway.reward.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:38 2018/5/3
 * @Modified By:
 */
public class CreateRedeemCodeS2B extends S2BMessage{
    private List<String> redeemCodeList;

    public List<String> getRedeemCodeList() {
        return redeemCodeList;
    }

    public void setRedeemCodeList(List<String> redeemCodeList) {
        this.redeemCodeList = redeemCodeList;
    }
}
