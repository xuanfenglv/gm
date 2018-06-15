package com.longma.mopet.gm.gateway.reward.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:25 2018/5/4
 * @Modified By:
 */
public class LookRedeemCodeS2B extends S2BMessage {
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
