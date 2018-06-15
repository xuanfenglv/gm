package com.longma.mopet.gm.gateway.user.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:50 2018/4/25
 * @Modified By:
 */
public class SelectOperationListS2B extends S2BMessage{
    private String operations;

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
