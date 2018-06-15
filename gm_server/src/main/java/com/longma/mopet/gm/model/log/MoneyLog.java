package com.longma.mopet.gm.model.log;

import com.longma.mopet.gm.model.log.base.BaseLog;

import javax.persistence.Entity;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:39 2018/5/9
 * @Modified By:
 */
@Entity
public class MoneyLog extends BaseLog{
    private int sceneId;
    private int roomId;
    private int coinType;
    private int changeValue;
    private int finalValue;
    private int operate;
    private String note;

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(int changeValue) {
        this.changeValue = changeValue;
    }

    public int getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
