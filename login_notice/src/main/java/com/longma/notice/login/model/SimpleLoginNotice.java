package com.longma.notice.login.model;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:04 2018/5/21
 * @Modified By:
 */
public class SimpleLoginNotice {
    private int id;
    private String title;
    private String imgs;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
