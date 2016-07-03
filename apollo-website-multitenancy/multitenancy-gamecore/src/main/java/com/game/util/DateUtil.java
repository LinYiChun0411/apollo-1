package com.game.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.jay.frame.util.StringUtil;

public class DateUtil {

	private static final Calendar calendar = Calendar.getInstance();
	public static final String DATETIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_STR = "yyyy-MM-dd";
	public static final String DATE_YEAR_MONTH_FORMAT = "yyyyMM";

	// public static final SimpleDateFormat datetimeFormat = new
	// SimpleDateFormat(DATETIME_FORMAT_STR);
	// public static final SimpleDateFormat dateFormat = new
	// SimpleDateFormat(DATE_FORMAT_STR);
	// public static final SimpleDateFormat monthFormat = new
	// SimpleDateFormat(DATE_YEAR_MONTH_FORMAT);

	/**
	 * str转Date yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date toDatetime(String dateStr) {
		if (StringUtil.isEmpty(dateStr)) {
			return null;
		}

		try {
			SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT_STR);
			return datetimeFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * str转Date yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date toDate(String dateStr) {

		if (StringUtil.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Date转 str yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toDatetimeStr(Date date) {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT_STR);
		return datetimeFormat.format(date);
	}

	/**
	 * Date转 str yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String toDateStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
		return dateFormat.format(date);
	}

	/**
	 * 获取格式化后的当前日期字符串
	 * 
	 * @return yyyy
	 */
	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}

	/**
	 * 获取格式化后的当前日期字符串
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
		return dateFormat.format(new Date());
	}

	/**
	 * 获取指定格式的当前日期字符串
	 * 
	 * @return 自定义日期
	 */
	public static String getCurrentDate(String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(new Date());
	}

	/**
	 * 获取格式化后的当前时间字符串
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT_STR);
		return datetimeFormat.format(new Date());
	}

	/**
	 * 获取指定格式的当前时间字符串
	 * 
	 * @return 自定义时间
	 */
	public static String getCurrentTime(String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.US);
		return sdf.format(new Date());
	}

	/**
	 * 获取指定日期的指定格式的时间字符串
	 * 
	 * @return 自定义日期
	 */
	public static String getCurrentTime(String formatString, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.US);
		return sdf.format(date);
	}

	/**
	 * 将传入的日期向前（或向后）滚动|amount|天
	 * 
	 * @param date
	 * @param a
	 * @return yyyy-MM-dd
	 */
	public static String getRollDay(Date date, int amount) {
		calendar.setTime(date);
		calendar.add(calendar.DATE, amount);
		Date newDate = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
		return dateFormat.format(newDate);
	}

	/**
	 * 将传入的日期向前（或向后）滚动|amount|天
	 * 
	 * @param date
	 * @param a
	 * @return yyyy-MM-dd HH:ss:mm
	 */
	public static String getRollDayTime(Date date, int amount) {
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, amount);
		Date newDate = calendar.getTime();
		SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT_STR);
		return datetimeFormat.format(newDate);
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day < 0) {
			day = 0;
		}
		return weekDays[day];
	}

	/**
	 * 取得两个日期相差的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDayMargin(Date beginDate, Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 1,
				0, 0);
		long beginTime = calendar.getTime().getTime();
		calendar.setTime(endDate);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 1,
				0, 0);
		long endTime = calendar.getTime().getTime();
		return (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 取得两个日期相差的天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getDayMargin(String beginDate, String endDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.toDate(beginDate));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 1,
				0, 0);
		long beginTime = calendar.getTime().getTime();
		calendar.setTime(DateUtil.toDate(endDate));
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 1,
				0, 0);
		long endTime = calendar.getTime().getTime();
		return (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 获取当前所在的年月
	 * 
	 * @return
	 */
	public static String getYearMonth() {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return String.valueOf(year) + (month < 10 ? "0" + month : month);
	}

	/**
	 * yyyy-MM 转yyyyMM格式
	 * 
	 * @return
	 */
	public static String getYearMonth(Date strDate) {
		if (StringUtil.isEmpty(strDate)) {
			strDate = new Date();
		}
		SimpleDateFormat monthFormat = new SimpleDateFormat(DATE_YEAR_MONTH_FORMAT);
		return monthFormat.format(strDate);
	}

	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}
		long now = new Date().getTime();
		if (now - date.getTime() < 24 * 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	public static Date getTomorrow(Date curDate) {
		if(curDate == null){
			return null;
		}
		calendar.setTime(curDate);
		calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	public static void main(String[] args) {
		Date d = DateUtil.toDate("2015-08-26 15:00:00");
		System.out.println(DateUtil.isToday(d));
	}
}
