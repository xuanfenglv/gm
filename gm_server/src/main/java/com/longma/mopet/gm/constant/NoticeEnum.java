package com.longma.mopet.gm.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:Lvxingqing
 * @Description: 公告交换器
 * @Date:Create in 18:05 2018/5/16
 * @Modified By:
 */
public enum NoticeEnum {
    TIMING(1,"notice.timing"),
    LOGIN(2,"notice.login"),
    PUSH(3,"notice.push");

    private int type;
    private String exchange;

    NoticeEnum(int type, String exchange) {
        this.type = type;
        this.exchange = exchange;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public static String getExchangeNameByType(int type) {
        for (NoticeEnum notice : values()) {
            if (notice.getType() == type) {
                return notice.getExchange();
            }
        }
        return null;
    }
    private static Set<Integer> types = new HashSet<>();
    static {
        for (NoticeEnum notice : values()) {
            types.add(notice.getType());
        }
    }
    public static boolean isExist(int type) {
        if (types.contains(type)) {
            return true;
        } else {
            return false;
        }
    }
}
