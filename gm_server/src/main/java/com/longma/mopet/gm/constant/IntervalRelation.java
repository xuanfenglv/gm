package com.longma.mopet.gm.constant;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:04 2018/5/9
 * @Modified By:
 */
public enum  IntervalRelation {
    LEFT_INTERSECT(1),
    被包含(2),
    RIGHT_INTERSECT(3),
    包含(4),
    相离(5);


    private int value;

    IntervalRelation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
