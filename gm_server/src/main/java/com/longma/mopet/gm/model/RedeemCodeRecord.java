package com.longma.mopet.gm.model;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.gateway.reward.b2s.CreateRedeemCodeB2S;
import com.longma.mopet.gm.gateway.reward.b2s.UpdateRedeemB2S;
import com.longma.mopet.gm.model.base.Record;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:56 2018/5/3
 * @Modified By:
 */
public class RedeemCodeRecord implements Record {
    private int id;
    private long codeId;
    private int num;
    private String name;
    private String desc;
    private long beginTime;
    private long endTime;;
    private String servers;
    private String channels;
    private int times;
    private String content;
    private int pause;

    public RedeemCodeRecord() {
    }
    public RedeemCodeRecord(CreateRedeemCodeB2S msg) {
        this.codeId = msg.getCodeId();
        this.num = msg.getNum();
        this.name = msg.getName();
        this.desc = msg.getDesc();
        this.beginTime = msg.getBeginTime();
        this.endTime = msg.getEndTime();
        this.servers = JSON.toJSONString(msg.getServers());
        this.channels = JSON.toJSONString(msg.getChannels());
        this.times = msg.getTimes();
        this.content = JSON.toJSONString(msg.getContent());
        this.pause = msg.getPause();
    }
    public RedeemCodeRecord(UpdateRedeemB2S msg) {
        this.codeId = msg.getCodeId();
//        this.num = msg.getNum();
        this.name = msg.getName();
        this.desc = msg.getDesc();
        this.beginTime = msg.getBeginTime();
        this.endTime = msg.getEndTime();
        this.servers = JSON.toJSONString(msg.getServers());
        this.channels = JSON.toJSONString(msg.getChannels());
        this.times = msg.getTimes();
        this.content = JSON.toJSONString(msg.getContent());
        this.pause = msg.getPause();
    }

    public RedeemCodeRecord(long codeId, int num, String name, String desc, long beginTime, long endTime, String servers, String channels, int times, String content, int pause) {
        this.codeId = codeId;
        this.num = num;
        this.name = name;
        this.desc = desc;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.servers = servers;
        this.channels = channels;
        this.times = times;
        this.content = content;
        this.pause = pause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
}
