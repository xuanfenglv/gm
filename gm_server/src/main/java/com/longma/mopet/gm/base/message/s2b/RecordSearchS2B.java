package com.longma.mopet.gm.base.message.s2b;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:19 2018/5/4
 * @Modified By:
 */
public class RecordSearchS2B extends S2BMessage {
    private int total = 0;
    private List recordList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
}
