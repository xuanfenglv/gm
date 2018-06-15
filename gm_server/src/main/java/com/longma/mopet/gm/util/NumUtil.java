package com.longma.mopet.gm.util;

import com.longma.mopet.gm.exception.NumberCastException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:09 2018/5/14
 * @Modified By:
 */
public class NumUtil {
    public static String getFullNum(int num, int len) throws Exception{
        if(len<2){
            throw new NumberCastException("被转化的长度不得小于2");
        }
        int max = 10^len;

        String s_num = String.valueOf(num);
        if(s_num.length()>len){
            throw new NumberCastException("被转化数字的长度超过设置的长度");
        }
        int bu = len - s_num.length();
        String zeros = genZero(bu);
        String rs = zeros+s_num;
        return rs;
    }

    public static String genZero(int len) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i++) {
            sb.append(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(getFullNum(10,4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
