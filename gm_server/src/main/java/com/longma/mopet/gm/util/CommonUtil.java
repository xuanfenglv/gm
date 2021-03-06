package com.longma.mopet.gm.util;

import java.util.List;

public class CommonUtil {
	public static int parseInt(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	public static int parseInt(Integer num, int defaultValue) {
		if(num==null) {
			return defaultValue;
		}
		return num;
	}
	public static int sum(List<Integer> numList) {
		int sum=0;
		for (Integer item : numList) {
			if (item != null) {
				sum+=item;
			}
		}
		return sum;
	}
}
