package com.longma.mopet.gm.constant;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:00 2018/5/7
 * @Modified By:
 */
public enum GMRewardReason {
    REPORT_BUG(1,"报告bug"),
    INNER_TEST(2,"内部测试");

    private int id;
    private String name;

    GMRewardReason(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static boolean constains(int id) {
        for(GMRewardReason tem : GMRewardReason.values()) {
            if(tem.id==id) {
                return true;
            }
        }
        return false;
    }
}
