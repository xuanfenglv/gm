package com.longma.mopet.gm.controller.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:00 2018/5/18
 * @Modified By:
 */
public class ImgListS2B extends S2BMessage {
    private List<String> imgs;

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
