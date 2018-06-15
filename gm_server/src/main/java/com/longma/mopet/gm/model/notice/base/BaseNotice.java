package com.longma.mopet.gm.model.notice.base;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.base.message.b2s.AddNoticeB2S;
import com.longma.mopet.gm.model.base.Record;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:33 2018/5/10
 * @Modified By:
 */
public abstract class BaseNotice implements Record {
    private int id;
    private long beginTime;
    private String servers;
    private String channels;
    private String content;
    private int pause;

    public BaseNotice() {
    }

    public BaseNotice(AddNoticeB2S b2S) {
        id = b2S.getId();
        beginTime = b2S.getBeginTime();
        servers = JSON.toJSONString(b2S.getServers());
        channels = JSON.toJSONString(b2S.getChannels());
        content = b2S.getContent();
        pause = b2S.getPause();
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

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
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
