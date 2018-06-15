package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

/**
 * @Author:Lvxingqing
 * @Description: 记录查询的抽象类
 * @Date:Create in 11:08 2018/5/4
 * @Modified By:
 */
public abstract class RecordSearchB2S extends B2SMessage {
    // 1=分页查看，2=全部导出为excel
    private int type;
    // 第几页
    private int pageNum;
    // 每页显示几条
    private int pageSize;

    public abstract String genCondition();
    public String genLimit() {
        String limit = null;
        if(type==1) {
            int offset = pageSize*(pageNum-1);
            limit ="limit "+offset+","+pageSize;
        }
        return limit;
    }
    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            if(type==1) {
                if(!(pageSize==5||pageSize==10||pageSize==15||pageSize==20||pageSize==50||pageSize==100)) {
                    result.setErrMsg("非法的分页尺寸");
                } else if(pageNum<1) {
                    result.setErrMsg("页数不小于1");
                }
            } else if(type!=2) {
                result.setErrMsg("错误的查询类型");
            }
        }
        return result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
