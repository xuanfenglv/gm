package com.longma.mopet.gm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description:时间工具类
 * @author nanjun.li
 * @date 2011-6-23
 */
public class DateUtil {
	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static DateFormat giftBagDateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	private static DateFormat logDateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static DateFormat mailDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	private static SimpleDateFormat dayFormater = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static SimpleDateFormat timerOfDayFormater = new SimpleDateFormat(
			"HH:mm:ss");
	private static SimpleDateFormat dayNum = new SimpleDateFormat(
			"yyyyMMdd");
	private static DateFormat dateFormatMs = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");
	// 一天的毫秒数
	public static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

	public static void parserDateFormat() {

	}

	/**
	 * 生成默认日期格式
	 * @param date
	 * @return
	 */
	public static String getDefaultFormatByDate(Date date) {
		return dateFormat.format(date);
	}
	public static String getDefaultFormatByTime(long time) { return getDefaultFormatByDate(new Date(time));}
	public static long getGiftBagTimeByDateStr(String str) {
		long time = 0l;
		if (str.equals("-1")) {
			return -1l;
		}
		try {
			time = giftBagDateFormat.parse(str).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	public static long getBeginOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		System.out.println(calendar.getTime());
		return calendar.getTimeInMillis();
	}
	public static long getEndOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		System.out.println(calendar.getTime());
		return calendar.getTimeInMillis();
	}

	/**
	 * 生成日志日期
	 * @param date
	 * @return
	 */
	public static String getLogFormatByDate(Date date) {
		return logDateFormat.format(date);
	}

	/* 日志格式属性的getter方法 */
	public static DateFormat getLogDateFormat() {
		return logDateFormat;
	}
	public static Date getLogDateFormat(String date) {
		try {
			return logDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getDayFormat(Date date) {
		return dayFormater.format(date);
	}
	/*次日凌晨3点定时器*/
	public static final Date TIMER_THREE_START_DATE;
	static {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		TIMER_THREE_START_DATE = calendar.getTime();
	}

	/*** 定时器启动的默认时间 为次日的凌晨 */
	public static final Date TIMER_DEFAULT_START_DATE;

	static {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		TIMER_DEFAULT_START_DATE = calendar.getTime();

	}
	/*次日中午12点定时器*/
	public static final Date TIMER_START_DATE_NOON;

	static {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		TIMER_START_DATE_NOON = calendar.getTime();
	}
	/*得到次日凌晨date*/
	public static Date getTomorrowZeroHour() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
	/*得到下一小时整点date*/
	public static Date getNextZeroHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/*得到12小时后整点date*/
	public static Date getNoonHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 两个时间之间的天数
	 * type:yyyy-MM-dd
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 获取标准时间字符串的时间戳
	 * @param date
	 * @return
	 */
	public static long getDateTime(String date) {

		long day = 0;

		try {
			day = dateFormat.parse(date).getTime();
		} catch (Throwable e) {

		}

		return day;
	}
	/**
	 * 获取邮箱时间字符串的时间戳
	 * @param date
	 * @return
	 */
	public static long getMailDateTime(String date) {

		long day = 0;

		try {
			day = mailDateFormat.parse(date).getTime();
		} catch (Throwable e) {

		}

		return day;
	}

	/**
	 * 获取默认时间格式的date
	 * @param date
	 * @return
	 */
	public static Date getDefaultDate(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取毫秒级时间格式的date
	 * @param date
	 * @return
	 */
	public static Date getMsDate(String date) {
		try {
			return dateFormatMs.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回第二天的时间 yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = c.getTime();
		String t = dayFormater.format(tomorrow);
		try {
			tomorrow = dayFormater.parse(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tomorrow;
	}

	/**
	 * 
	 * @Description 返回第二天早点6点的时间 yyyy-MM-dd 06:00:00
	 * @author linanjun
	 * @date 2012-12-26 下午02:46:53
	 * @return Date
	 */
	public static Date getTomorrowSixHours() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.add(Calendar.HOUR_OF_DAY, 6);
		Date date = c.getTime();
		String t = dayFormater.format(date);
		try {
			date = dayFormater.parse(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取几天前或者几天后的时间，具体为：YYYY-mm-dd 00:00:00
	 * 
	 * @param days
	 * 
	 * @return
	 */
	public static Calendar getDateTimeByDay(int days) {
		Calendar _date = Calendar.getInstance();
		_date.set(Calendar.AM_PM, Calendar.AM);
		_date.set(Calendar.HOUR, 0);
		_date.set(Calendar.MINUTE, 0);
		_date.set(Calendar.SECOND, 0);
		_date.add(Calendar.DAY_OF_YEAR, days);
		return _date;
	}

	/**
	 * 
	 * @Description 获取N个小时之后的时间
	 * @author linanjun
	 * @date 2013-3-20 下午05:25:20
	 * @param days
	 * @param hour
	 * @return Calendar
	 */
	public static Calendar getDateTimeByNextHour(long days, int hour) {
		Calendar _date = Calendar.getInstance();
		_date.setTimeInMillis(days);
		_date.add(Calendar.HOUR_OF_DAY, hour);
		return _date;
	}

	public static Date getDayOfWeek(Date date, int dayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		//
		calendar.setTime(date);
		// 设置到本周几
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

		if (calendar.getTime().getTime() >= date.getTime()) {
			// 是之后的直接返回
			return calendar.getTime();
		}

		calendar.add(Calendar.WEEK_OF_MONTH, 1);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

		Date nowDate = calendar.getTime();

		return nowDate;
	}

	/**
	 * 
	 * @Description 获取几天前或者几天后的时间，具体为：YYYY-mm-dd 00:00:00
	 * @author linanjun
	 * @date 2012-10-23 下午12:06:10
	 * @param oldTime
	 * @param keepHour
	 * @return String
	 */
	public static String getDateTimeByDay(long oldTime, int keepHour) {
		Calendar _date = Calendar.getInstance();
		_date.setTimeInMillis(oldTime);
		/** 取出旧的时间在当天的哪一小时 */
		int day_hour = _date.get(Calendar.HOUR_OF_DAY);
		/** 计算新的时间 */
		_date.set(Calendar.HOUR_OF_DAY, day_hour + keepHour);
		return getDefaultFormatByDate(_date.getTime());
	}

	/**
	 * @Description Long型数据转化为时间类型的方法
	 * @author linanjun
	 * @date 2012-4-24 下午4:36:45
	 * @param l
	 * @return
	 * @throws ParseException
	 *             Date
	 */
	public static Date longToDate(Long l) throws ParseException {
		Date date = new Date();
		date.setTime(l);
		SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd EEE a hh:mm:ss ");
		date = strChangeToDate(formater.format(date));
		return date;
	}

	/**
	 * @Description 由字符串（指定）格式（yyyy-MM-dd HH:mm:SS）转化成Date
	 * @author linanjun
	 * @date 2012-4-24 下午4:35:36
	 * @param str
	 * @return Date
	 */
	public static Date strChangeToDate(String str) {
		Date date = null;
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			date = sd.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Description 将date转换为毫秒
	 * @author linanjun
	 * @date 2013-1-10 下午04:06:44
	 * @param date
	 * @return long
	 */
	public static long dateChangeToLong(Date date) {
		Calendar _date = Calendar.getInstance();
		_date.setTime(date);
		return _date.getTimeInMillis();
	}

	public static Date getTodayLastTime() {
		return null;
	}

	public static boolean isYesterday(long lastRequestRewardTime) {

		if (0 == lastRequestRewardTime) {
			return false;
		}

		Calendar calendar1 = Calendar.getInstance();

		calendar1.setTime(new Date(lastRequestRewardTime));

		Calendar calendar2 = Calendar.getInstance();

		calendar2.setTime(new Date());

		calendar2.add(Calendar.DATE, -1);

		if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return false;
		}

		if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)) {
			return false;
		}

		if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2
				.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}

		return true;
	}

	public static boolean isToday(long lastRequestRewardTime) {

		if (0 == lastRequestRewardTime) {
			return false;
		}

		Calendar calendar1 = Calendar.getInstance();

		calendar1.setTime(new Date(lastRequestRewardTime));

		Calendar calendar2 = Calendar.getInstance();

		calendar2.setTime(new Date());

		if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return false;
		}

		if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)) {
			return false;
		}

		if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2
				.get(Calendar.DAY_OF_MONTH)) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	// public static long getLongDate(Date date) {
	// Calendar calendar = Calendar.getInstance();
	//
	// calendar.setTime(date);
	//
	// /************************************************************************
	// * + + + + + + + + + *
	// * 0 8 16 24 32 40 48 56 64 *
	// * +-------+-------+-------+-------+-------+-------+-------+-------+ *
	// * + year +month +day +hour +minute +seconds+ + *
	// * +-------+-------+-------+-------+-------+-------+-------+-------+ *
	// ************************************************************************/
	//
	// // 0xFFFFFFFFFFFFFFFFL
	// // 0x00FFFFFFFFFFFFFFL
	// // 0x0000FFFFFFFFFFFFL
	// // 0x000000FFFFFFFFFFL
	// // 0x00000000FFFFFFFFL
	// // 0x0000000000FFFFFFL
	// // 0x000000000000FFFFL
	// // 0x00000000000000FFL
	//
	// long year = calendar.get( Calendar.YEAR );
	// long month = calendar.get( Calendar.MONTH );
	// long day = calendar.get( Calendar.DAY_OF_MONTH );
	// long hour = calendar.get( Calendar.HOUR_OF_DAY );
	// long minute = calendar.get( Calendar.MINUTE );
	// long seconds = calendar.get( Calendar.SECOND );
	//
	// long result = 0;
	//
	// return result;
	// }

	public static String timeOfDayFormater(Date date) {
		return timerOfDayFormater.format(date);
	}

	/**
	 * 返回本月最后1秒
	 * @return
	 */
	public static Date getMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 返回下个月最后1秒
	 * @return
	 */
	public static Date getNextMonthEnd() {
		Calendar calendar = Calendar.getInstance();

		calendar.roll(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天前的时间（重载）
	 * 
	 * @param d
	 *            字符串类型的时间
	 * @param day
	 * @return String类型的时间
	 */
	public static String getDateBefore(String d, int day) {
		return DateUtil.getDefaultFormatByDate(getDateBefore(
				strChangeToDate(d), day));
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR) + day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间（重载）
	 * 
	 * @param d
	 *            字符串类型的时间
	 * @param day
	 * @return String类型的时间
	 */
	public static String getDateAfter(String d, int day) {
		return DateUtil.getDefaultFormatByDate(getDateAfter(
				strChangeToDate(d), day));
	}

	/**
	 * @Description 获取date的零点正
	 * @author pingyang.li
	 * @date 2013-6-7 上午11:49:36
	 * @param date
	 * @return long
	 */
	public static Date getZeroDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当天时间的最后时间 23:59:59
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastTimeByDay(Date d) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR_OF_DAY, 23);
		now.set(Calendar.MINUTE, 59);
		now.set(Calendar.SECOND, 59);

		return now.getTime();
	}

	/**
	 * 获取当天时间的开始时间 00:00:00
	 * 
	 * @param d
	 * @return
	 */
	public static Date getBeginTimeByDay(Date d) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR_OF_DAY, 00);
		now.set(Calendar.MINUTE, 00);
		now.set(Calendar.SECOND, 00);

		return now.getTime();
	}

	/**
	 * 获取本周最后1秒
	 * @return
	 */
	public static Date getWeekEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取下周最后一分钟
	 * @return
	 */
	public static Date getNextWeekEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}

	public static boolean getWeekDay(Date date, int weekday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == weekday;
	}

	public static Date getMonthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getNextMonthStart() {
		Calendar calendar = Calendar.getInstance();

		calendar.roll(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static boolean isOneDay(long time1, long time2) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(time1);

		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.setTimeInMillis(time2);

		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);

		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		}

		return false;
	}

	public static Date getTodayHHmmss(String HHmmss) {

		String[] array = HHmmss.split(":");
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(array[0]));
		calendar.set(Calendar.MINUTE, Integer.valueOf(array[1]));
		calendar.set(Calendar.SECOND, Integer.valueOf(array[2]));

		return calendar.getTime();

	}

	/**
	 * 把date的时分秒转化为今天的时分秒
	 * @param date
	 * @return
	 */
	public static Date getTodayHHmmss(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String str = "";

		str = str + calendar.get(Calendar.HOUR_OF_DAY) + ":";
		str = str + calendar.get(Calendar.MINUTE) + ":";
		str = str + calendar.get(Calendar.SECOND);

		return getTodayHHmmss(str);

	}

	/**
	 * 
	 * @Description 把descDate的时分秒转到srcDate的时分秒
	 * @author
	 * @date 2013-4-24 下午05:04:18
	 * @param srcDate
	 * @param descDate
	 * @return Date
	 */
	public static Date copyHHmmss(Date srcDate, Date descDate) {

		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();

		calendar.setTime(srcDate);
		calendar1.setTime(descDate);

		calendar.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));

		return calendar.getTime();
	}

	/**
	 * 消除毫秒数
	 * @param date
	 * @return
	 */
	public static Date deleteMillisecond(Date date) {
		return new Date((date.getTime() / 1000) * 1000);
	}

	/**
	 * 
	 * @Description 返回两天之间相差的天数, 并假设 time2 >= time1. 如果time2 < time1 则返回-1.
	 * 
	 * @author
	 * @date 2013-5-23 下午10:16:11
	 * @param time1
	 * @param time2
	 * @return int
	 */
	public static int getdays(long time1, long time2) {
		if (time1 > time2) {// 正常情况下不可能发生
			return -1;
		}

		if (time1 == time2 || isOneDay(time1, time2)) {
			return 0;
		}

		int days = (int) ((time2 - time1) / (long) (24 * 60 * 60 * 1000));

		if ((time2 - time1) % (long) (24 * 60 * 60 * 1000) > 1) {
			days = days + 1;
		}
		return days;
	}
	/*明天中午12点date*/
	public static Date getNextMoonDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Discription
	 * @author nanjun.li
	 * @created 2017-3-28 上午10:36:34
	 * @param type
	 *            1=当前年份,2=当前月份,3=当前天
	 * @return
	 */
	public static int getCurrentMonth(int type) {
		Calendar calendar = Calendar.getInstance();
		switch (type) {
		case 1:
			return calendar.get(Calendar.YEAR) + 1;
		case 2:
			return calendar.get(Calendar.MONTH) + 1;
		case 3:
			return calendar.get(Calendar.DAY_OF_MONTH) + 1;
		default:
			break;
		}
		return 0;
	}

	/**
	 * 取得当月天数
	 * */
	public static int getCurrentMonthLastDay() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到当前周数，周日开始
	 * */
	public static int getWeekNum1() {
		Calendar c = Calendar.getInstance();
		int week = c.get(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		int weekNum = Integer.parseInt(year + "" + week);
		return weekNum;
	}
	/**
	 * 得到当前周数，周一开始
	 * */
	public static int getWeekNum() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		int year = c.get(Calendar.YEAR);
		int weekNum = Integer.parseInt(year + "" + week);
		return weekNum;
	}

	/**
	 * 得到当前天数20170426
	 * */
	public static int getDayNum() {
		String s_dayNum = dayNum.format(new Date());
		int dayNum = Integer.parseInt(s_dayNum);
		return dayNum;
	}

	public static List<String> getBetweenDate(String beginTime, String endTime) {

		String begin = beginTime;
		String end = endTime;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Date d1 = null;
		Date d2 = null;
		try {
			if(begin==null||begin.equals("")) {
				d1=new Date();
			} else {
				d1 = sdf.parse(begin);
			}
			if(end==null||end.equals("")) {
				d2=new Date();
			} else {
				d2 = sdf.parse(end);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> result = new ArrayList<String>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(d1);

		while (d1.getTime() <= d2.getTime()) {
			result.add(sdf.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			d1 = tempStart.getTime();
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getEndOfToday());
	}

}
