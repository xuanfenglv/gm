package com.longma.mopet.gm.model.notice;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.gateway.notice.b2s.AddLoginNoticeB2S;
import com.longma.mopet.gm.model.notice.base.BaseIntervalNotice;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:38 2018/5/10
 * @Modified By:
 */
public class LoginNotice extends BaseIntervalNotice {
    private String title;
    private String imgs;

    public LoginNotice() {
    }

    public LoginNotice(AddLoginNoticeB2S b2S) {
        super(b2S);
        this.title = b2S.getTitle();
        this.imgs = JSON.toJSONString(b2S.getImgs());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
