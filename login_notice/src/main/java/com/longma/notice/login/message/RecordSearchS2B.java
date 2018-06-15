package com.longma.notice.login.message;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:51 2018/5/21
 * @Modified By:
 */
public class RecordSearchS2B extends S2BMessage{
    private List recordList;

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
}
