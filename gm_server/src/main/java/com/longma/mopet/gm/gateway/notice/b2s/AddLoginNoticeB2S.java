package com.longma.mopet.gm.gateway.notice.b2s;

import com.longma.mopet.gm.base.message.b2s.AddIntervalNoticeB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description: 登录公告
 * @Date:Create in 17:12 2018/5/10
 * @Modified By:
 */
public class AddLoginNoticeB2S extends AddIntervalNoticeB2S {

    private String title;
    private String[] imgs;
    @Override
    protected void doCheck(CheckParamResult result) {
        // 或许需要校验下content的长度，url的有效性；
        if (StringUtil.isEmpty(title)) {
            result.setErrMsg("标题为空");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }
}
