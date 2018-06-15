package com.longma.mopet.gm.model;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:28 2018/5/3
 * @Modified By:
 */
public class RedeemCode {
    private int id;
    private String redeem_code;
    private long codeId;
    private int lastTimes;

    public RedeemCode() {
    }

    public RedeemCode(String redeem_code, long codeId, int lastTimes) {
        this.redeem_code = redeem_code;
        this.codeId = codeId;
        this.lastTimes = lastTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRedeem_code() {
        return redeem_code;
    }

    public void setRedeem_code(String redeem_code) {
        this.redeem_code = redeem_code;
    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }

    public int getLastTimes() {
        return lastTimes;
    }

    public void setLastTimes(int lastTimes) {
        this.lastTimes = lastTimes;
    }
}
