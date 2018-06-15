package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:13 2018/5/10
 * @Modified By:
 */
public abstract class AddNoticeB2S extends AimAtServerAndChannelB2S {
    // 加了id后，add和update 公告通用一个消息
    private int id;
    private long beginTime;

    // 公告内容
    private String content;
    // 1=暂停，0=不暂停
    private int pause;
    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if (result.isValid()) {
            //
            if(StringUtil.isEmpty(content)) {
                result.setErrMsg("公告内容为空");
            } else if(beginTime<=System.currentTimeMillis()) {
                result.setErrMsg("公告开始时间小于当前时间");
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
}
