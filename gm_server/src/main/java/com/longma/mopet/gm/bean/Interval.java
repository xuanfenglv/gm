package com.longma.mopet.gm.bean;

import com.longma.mopet.gm.constant.IntervalRelation;
import com.longma.mopet.gm.exception.IntervalException;

/**
 * @Author:Lvxingqing
 * @Description: [,)
 * @Date:Create in 15:55 2018/5/9
 * @Modified By:
 */
public class Interval {
    private int l;
    private int r;

    public Interval(int l, int r) {
        if(l>=r) {
            throw new IntervalException("左区间>=右区间");
        }
        this.l = l;
        this.r = r;
    }
    /**
     * @Author: Lvxianqing
     * @Description: 判断2个区间是否有交集
     * @Date: 2018-05-09 16:58:45
     * @param i1
     * @param i2
     */
    public static boolean isIntersect(Interval i1, Interval i2) {
        if(i2.getL()>=i1.getR()||i2.getR()<=i1.getL()) {
            return false;
        }
        return true;
    }
    /**
     * @Author: Lvxianqing
     * @Description: 判断区间的关系
     * @Date: 2018-05-09 17:22:46
     * @param other
     */
    public IntervalRelation relation(Interval other) {
        IntervalRelation relation = null;
        if(isIntersect(this,other)) {
            if(this.getL()<=other.getL()&&this.getR()>other.getL()) {
                if(other.getR()>this.getR()) {
                    relation = IntervalRelation.LEFT_INTERSECT;
                } else {
                    relation = IntervalRelation.包含;
                }
            } else {
                if(other.getR()>this.getR()) {
                    relation = IntervalRelation.被包含;
                } else {
                    relation = IntervalRelation.RIGHT_INTERSECT;
                }
            }
        } else {
            relation = IntervalRelation.相离;
        }
        return relation;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
