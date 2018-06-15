package com.longma.mopet.gm.bean;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:32 2018/5/9
 * @Modified By:
 */
public class Limit {
    // 1=左相交,2=包含,3=右相交,4=被包含,
    private int type = 2;
    private int offset;
    private int len;

    /**
     * @desc 生成limit语句
     * @return
     */
    public String genLimit() {
        if(type!=2) {
            return "limit " + offset + "," + len;
        }
        return "";
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
