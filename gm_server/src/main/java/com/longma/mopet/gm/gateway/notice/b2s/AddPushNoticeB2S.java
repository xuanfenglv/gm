package com.longma.mopet.gm.gateway.notice.b2s;

import com.longma.mopet.gm.base.message.b2s.AddNoticeB2S;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:31 2018/5/18
 * @Modified By:
 */
public class AddPushNoticeB2S extends AddNoticeB2S {
    @Override
    protected void doCheck(CheckParamResult result) {
        if (getContent().length() > 100) {
            result.setErrMsg("公告内容长度不超过500");
        }
    }
}
