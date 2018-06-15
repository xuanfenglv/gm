package com.longma.mopet.gm.gateway.user.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:09 2018/4/19
 * @Modified By:
 */
@Component
public class SuspendB2S extends B2SMessage {
    // 被封号者名字
    private String name;
    // 解封的时间戳
    private long deadline;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(deadline<=System.currentTimeMillis()) {
            result.setErrMsg("封号时间不得小于当前时间");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public long getDeadline() {
        return deadline;
    }
}
