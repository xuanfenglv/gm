package com.longma.mopet.gm.gateway.worldmanage.b2s;

import com.longma.mopet.gm.base.message.b2s.GameRecordB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:30 2018/5/22
 * @Modified By:
 */
public class RoleInfoSearchB2S extends GameRecordB2S {
    private long uuid;
    private long passportId;
    private String name;
    @Override
    protected void doCheck(CheckParamResult result) {

    }

    @Override
    public String genCondition() {
        StringBuilder sb = new StringBuilder();
        if(uuid!=0){
            sb.append(" and uuid=").append(uuid);
        } else {
            if (passportId != 0) {
                sb.append(" and passportId=").append(passportId);
            }
            if (!StringUtil.isEmpty(name)) {
                sb.append(" and `name` like '%").append(name).append("%'");
            }
        }
        return sb.toString();
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public long getPassportId() {
        return passportId;
    }

    public void setPassportId(long passportId) {
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
