package com.longma.mopet.gm.model;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 18:02 2018/4/19
 * @Modified By:
 */
public class SuspendRecord {
    // 流水号
    private int id;
    // 被封号的账号
    private String name;
    // 封号时间
    private long fengtime;
    // 封号原因(100)
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFengtime() {
        return fengtime;
    }

    public void setFengtime(long fengtime) {
        this.fengtime = fengtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
