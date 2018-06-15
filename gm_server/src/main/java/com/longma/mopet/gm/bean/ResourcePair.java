package com.longma.mopet.gm.bean;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:41 2018/6/5
 * @Modified By:
 */
public class ResourcePair {
    private int id;
    private String name;

    public ResourcePair(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
