package com.longma.mopet.gm.gateway.worldmanage.b2s;

import com.longma.mopet.gm.base.message.b2s.GameRecordB2S;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.DateUtil;
import com.longma.mopet.gm.util.StringUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:33 2018/5/10
 * @Modified By:
 */
public class UserInfoSearchB2S extends GameRecordB2S {
    private long id;
    private String name;
    private String lastLoginIp;
    private int regionId;
    private long loginBeginTime;
    private long loginEndTime;
    private int role;
    private int state;
    private long onlineTimeBeginTime;
    private long onlineTimeEndTime;
    private long logoutBeginTime;
    private long logoutEndTime;

    @Override
    public String genCondition() {
        StringBuilder sb = new StringBuilder();
        if(id!=0){
            sb.append(" and id=").append(id);
        } else {
            if(!StringUtil.isEmpty(name)) {
                sb.append(" and `name` like '%").append(name).append("%'");
            }
            if (!StringUtil.isEmpty(lastLoginIp)) {
                sb.append(" and lastLoginIp='").append(lastLoginIp).append("'");
            }
            if (regionId!=0) {
                sb.append(" and regionId=").append(regionId);
            }
            if (loginBeginTime!=0) {
                sb.append(" and lastLoginDate>='").append(DateUtil.getDefaultFormatByTime(loginBeginTime)).append("'");
            }
            if (loginEndTime!=0) {
                sb.append(" and lastLoginDate<'").append(DateUtil.getDefaultFormatByTime(loginEndTime)).append("'");;
            }
            if (logoutBeginTime!=0) {
                sb.append(" and lastLogoutTime>='").append(DateUtil.getDefaultFormatByTime(logoutBeginTime)).append("'");;
            }
            if (logoutEndTime!=0) {
                sb.append(" and lastLogoutTime<'").append(DateUtil.getDefaultFormatByTime(logoutEndTime)).append("'");;
            }
            if (onlineTimeBeginTime!=0) {
                sb.append(" and todayOnlineTime>=").append(onlineTimeBeginTime);
            }
            if (onlineTimeEndTime!=0) {
                sb.append(" and todayOnlineTime<").append(onlineTimeEndTime);
            }
            if(role!=-1){
                sb.append(" and role=").append(role);
            }
            if(state!=-1){
                sb.append(" and state=").append(state);
            }

        }
        return sb.toString();
    }

    @Override
    protected void doCheck(CheckParamResult result) {
        // later
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public long getLoginBeginTime() {
        return loginBeginTime;
    }

    public void setLoginBeginTime(long loginBeginTime) {
        this.loginBeginTime = loginBeginTime;
    }

    public long getLoginEndTime() {
        return loginEndTime;
    }

    public void setLoginEndTime(long loginEndTime) {
        this.loginEndTime = loginEndTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getOnlineTimeBeginTime() {
        return onlineTimeBeginTime;
    }

    public void setOnlineTimeBeginTime(long onlineTimeBeginTime) {
        this.onlineTimeBeginTime = onlineTimeBeginTime;
    }

    public long getOnlineTimeEndTime() {
        return onlineTimeEndTime;
    }

    public void setOnlineTimeEndTime(long onlineTimeEndTime) {
        this.onlineTimeEndTime = onlineTimeEndTime;
    }

    public long getLogoutBeginTime() {
        return logoutBeginTime;
    }

    public void setLogoutBeginTime(long logoutBeginTime) {
        this.logoutBeginTime = logoutBeginTime;
    }

    public long getLogoutEndTime() {
        return logoutEndTime;
    }

    public void setLogoutEndTime(long logoutEndTime) {
        this.logoutEndTime = logoutEndTime;
    }
}
