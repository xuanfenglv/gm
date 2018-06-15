package com.longma.mopet.gm.rabbitmq.message;

/**
 * @Author:Lvxingqing
 * @Description: 公告变化消息
 * @Date:Create in 16:47 2018/5/16
 * @Modified By:
 */
public class NoticeChangeMessage {
    // 1=重载，2=删除
    private int type;
    private long id;
    private long time;

    public static NoticeChangeMessage genReLoadMsg(long id) {
        return new NoticeChangeMessage(1, id);
    }
    public static NoticeChangeMessage genRemoveMsg(long id) {
        return new NoticeChangeMessage(2, id);
    }
    public NoticeChangeMessage() {
    }

    private NoticeChangeMessage(int type, long id) {
        this.type = type;
        this.id = id;
        this.time = System.currentTimeMillis();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
