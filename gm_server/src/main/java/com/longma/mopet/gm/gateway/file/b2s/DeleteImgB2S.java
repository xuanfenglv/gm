package com.longma.mopet.gm.gateway.file.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:06 2018/5/21
 * @Modified By:
 */
public class DeleteImgB2S extends B2SMessage{
    private List<String> imgs;
    @Override
    protected void doCheck(CheckParamResult result) {
        if (imgs == null || imgs.size() == 0) {
            result.setErrMsg("无选中的图片");
        }
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
