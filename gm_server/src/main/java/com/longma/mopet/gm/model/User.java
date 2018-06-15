package com.longma.mopet.gm.model;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 19:05 2018/4/18
 * @Modified By:
 */
public class User {
    // 用户名
    private String name;
    // 密码
    private String pwd;
    // 权限
    private int power;
    // 封号截止时间
    private long deadline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}
