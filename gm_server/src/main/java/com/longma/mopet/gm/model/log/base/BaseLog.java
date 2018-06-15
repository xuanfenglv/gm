package com.longma.mopet.gm.model.log.base;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:39 2018/5/9
 * @Modified By:
 */
public class BaseLog {
    private int id;// 对应数据库主键
    private int logType; // 日志类型 SharedConstants.LOG_TYPE_XXX
    private long logTime; // 日志时间，Java中的统一时间
    private int regionId; // 服务器区ID
    private int serverId; // 服务器ID
    private int clientType;// 客户端类型
    private String appId;// 客户端应用id
    private String deviceType;// 客户端设备类型
    private long accountId; // 账号id
    private String accountName;// 帐号名字
    private long charId; // 角色id
    private String charName;// 角色名字
    private int level; // 用户当前级别
    private int reason; // 日志的原因
    private String param; // 附加参数
    private long createTime;// 创建时间

    public BaseLog() {

    }

    public BaseLog(int type, long time, int rid, int sid, int clientType, String appId, String deviceType, long aid, String accountName,
                   long cid, String charName, int level, int reason, String param) {
        this.logType = type;
        this.logTime = time;
        this.regionId = rid;
        this.serverId = sid;
        this.clientType = clientType;
        this.appId = appId;
        this.deviceType = deviceType;
        this.accountId = aid;
        this.accountName = accountName;
        this.charId = cid;
        this.charName = charName;
        this.level = level;
        this.reason = reason;
        this.param = param;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public long getLogTime() {
        return logTime;
    }

    public void setLogTime(long logTime) {
        this.logTime = logTime;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getCharId() {
        return charId;
    }

    public void setCharId(long charId) {
        this.charId = charId;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
