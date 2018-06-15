package com.longma.mopet.gm.gateway.notice.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.constant.NoticeEnum;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:36 2018/5/15
 * @Modified By:
 */
public class OperateNoticeB2S extends B2SMessage {
    // 1=暂停，2=开始,3=删除
    private int operate;
    // 1=定时，2=登录，3=活动，4=推送
    private int type;
    // 公告id
    private int id;

    @Override
    protected void doCheck(CheckParamResult result) {
        if(operate<1||operate>3) {
            result.setErrMsg("不存在的操作类型");
        } else if (!NoticeEnum.isExist(type)) {
            result.setErrMsg("不存在的公告类型");
        }
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
