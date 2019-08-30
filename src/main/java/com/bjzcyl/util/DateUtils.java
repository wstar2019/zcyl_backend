package com.bjzcyl.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static Date today() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sf.format(date);
		try {
			return sf.parse(temp);
		} catch (ParseException e) {
			throw new RuntimeException("获取昨天失败", e);
		}
	}

	public enum Pattern {
		year, month, day, minutes, second, millisecond, hour
	};

	public static int dateDiff(Date date1, Date date2, Pattern p) {
		int diff = 0;
		Calendar cal1 = Calendar.getInstance();
		cal1.clear();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.clear();
		cal2.setTime(date2);
		switch (p) {
		case year: {
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			diff = year1 - year2;
			break;
		}
		case month: {
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			int month1 = cal1.get(Calendar.MONTH);
			int month2 = cal2.get(Calendar.MONTH);

			System.out.println(month1 + " " + month2);
			diff = month1 + (year1 - year2) * 12 - month2;
			break;
		}
		case minutes: {
			int minute1 = cal1.get(Calendar.MINUTE);
			int minute2 = cal2.get(Calendar.MINUTE);
			diff = minute1 - minute2;
			break;
		}
		case second: {
			int second1 = cal1.get(Calendar.SECOND);
			int second2 = cal2.get(Calendar.SECOND);
			diff = second1 - second2;
			break;
		}
		case millisecond: {
			int mil1 = cal1.get(Calendar.MILLISECOND);
			int mil2 = cal2.get(Calendar.MILLISECOND);
			diff = mil1 - mil2;
			break;
		}
		default:
			break;
		}
		return diff;
	}

	/**
	 * @param milliseconds
	 * @return
	 */
	public static Date getDateByMillSec(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		return c.getTime();
	}

	public static Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前时间减去12个月得到的时间
	 * 
	 * @return
	 */
	public static String getDateDiff() {
		Calendar c = Calendar.getInstance();
		String now = formatDate(c.getTime(), "yyyy-MM-dd");
		c.add(Calendar.MONTH, -11);
		String lnow = formatDate(c.getTime(), "yyyy-MM-dd");
		return now + "@" + lnow;
	}

	/**
	 * 得到系统当前时间
	 */
	public static String getNowDate() {
		Calendar c = Calendar.getInstance();
		String now = formatDate(c.getTime(), "yyyy-MM-dd");
		return now;
	}

	public static String getNowDate(String formatDate) {
		Calendar c = Calendar.getInstance();
		String now = formatDate(c.getTime(), formatDate);
		return now;
	}

	/**
	 * 得到当前时间减去12个月的时间
	 * 
	 * @return
	 */
	public static String getNowMonthsubtraction12() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -11);
		String snow = formatDate(c.getTime(), "yyyy-MM-dd");
		return snow;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @param noDay
	 * @return
	 */
	public static List<String> getDateMonth(String startDate, String endDate, boolean noDay) {
		Date date1 = parseDate(startDate);
		Date date2 = parseDate(endDate);
		List<String> list = new ArrayList<String>();
		System.out.println(date1);
		System.out.println(date2);
		int numMonth = dateDiff(date1, date2, Pattern.month);
		System.out.println(numMonth);
		for (int i = 0; i <= numMonth; i++) {
			Calendar c1 = new GregorianCalendar();
			c1.setTime(date1);
			c1.add(Calendar.MONTH, -i);
			if (noDay == true) {
				list.add(formatDate(c1.getTime(), "yyyy-MM"));
			} else {
				list.add(formatDate(c1.getTime(), "yyyy-MM-dd"));
			}
		}
		Collections.reverse(list);
		return list;
	}

	/**
	 * 得到一个月中的最后一天
	 */
	public static Integer getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.get(Calendar.DATE);
	}

	public static String getMDate(Date date) {
		String date1 = DateUtils.formatDate(date, "yyyy年MM月");
		return date1;
	}

	// 得到当前时间的下个月。
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		return calendar.getTime();
	}

	/**
	 * 得到当前时间的上个月
	 * 
	 * @author zxr
	 * @param date
	 * @return
	 */
	public static Date getPreDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 得到时间的前一天。
	 */
	public static Date getUpDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 得到时间的前7天。
	 */
	public static Date getUpDate7(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		return calendar.getTime();
	}
	
	/**
	 * 得到时间的下30天。
	 */
	public static Date getDownDate30(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +30);
		return calendar.getTime();
	}


	/**
	 * 得到时间的下一天。
	 */
	public static Date getDownDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return calendar.getTime();
	}
	
	/**
	 * 得到时间的下七天。
	 */
	public static Date getDownDate7(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +7);
		return calendar.getTime();
	}

	/**
	 * 获取当期时间 author gzb
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date dates = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String now = sdf.format(dates);
		return now;
	}

	/**
	 * 得打本季度
	 * 
	 * @return
	 */
	public static Integer getQuarter() {
		Calendar calendar = Calendar.getInstance();
		int quarter = calendar.get(Calendar.MONTH);
		if (quarter <= 12 && quarter >= 10)
			return 10;
		if (quarter <= 9 && quarter >= 7)
			return 7;
		if (quarter <= 6 && quarter >= 4)
			return 4;
		if (quarter <= 3 && quarter >= 1)
			return 1;
		return 0;
	}

	/**
	 * 得到当前时间减去12个月的时间
	 * 
	 * @return
	 */
	public static Date minusHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		return c.getTime();
	}

	public static String getYesterday() {
		Calendar d = Calendar.getInstance();
		d.add(Calendar.DATE, -1);
		return DateUtils.formatDate(d.getTime(), "yyyy-MM-dd");
	}
	public static String getToday() {
		return DateUtils.formatDate(new Date(), "yyyy-MM-dd");
	}

	public static Date yestoday() {
		Calendar d = Calendar.getInstance();
		d.add(Calendar.DATE, -1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sf.format(d.getTime());
		try {
			return sf.parse(temp);
		} catch (ParseException e) {
			throw new RuntimeException("获取昨天失败", e);
		}
	}



	/**
	 * 
	* @methods getUpDay 
	* @description <p>得到当前时间的前30分钟</p> 
	* @author luoyanni
	* @date 2014年12月22日 下午6:32:45
	 */
	public static Date getUpMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -30);
		return calendar.getTime();
	}
	
	/**
	 * 
	* @methods getDownDay 
	* @description <p>得到当前时间的后30分钟</p> 
	* @author luoyanni
	* @date 2014年12月22日 下午6:33:06
	 */
	public static Date getDownMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, +30);
		return calendar.getTime();
	}
	
	/**
	 * 得到指定日期的开始 如2010-01-10 00:00
	 * 
	 * @author zdw
	 * @param d
	 */
	public static Date getNowdayBegin(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getNowdayBeginMILLISECOND(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getNowdayEndMILLISECOND(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 59);
		return c.getTime();
	}

	/**
	 * 得到指定日期的结束 如2010-01-10 23:59
	 * 
	 * @author zdw
	 * @param d
	 */
	public static Date getNowdayEnd(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	// /**
	// * 得到当前时间减去3个月的时间
	// * @author ylchou
	// * @return
	// */
	// public static String getNowMonthsubtraction3() {
	// Calendar c = Calendar.getInstance();
	// c.add(Calendar.MONTH, -2);
	// String snow = formatDate(c.getTime(), "yyyy-MM-dd");
	// return snow;
	// }

	/**
	 * 得到当前时间减去3个月的时间
	 * 
	 * @return
	 */
	public static String getNowMonthsubtraction3() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		String snow = formatDate(c.getTime(), "yyyy-MM-dd");
		return snow;
	}

	/**
	 * 得到指定时间的开始时间 如:2010-10-10 00:00
	 * 
	 * @return
	 */
	public static Date getBegin(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return c.getTime();
	}

	/**
	 * 得到指定时间的结束时间 如:2010-10-10 23:59
	 * 
	 * @return
	 */
	public static Date getEnd(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return c.getTime();
	}

	/**
	 * 将指定格式日期形式的字符串转换成日期类型
	 * 
	 * <pre>
	 *   常用日期格式有:精确到秒的形式 &quot;yyyy-MM-dd HH:mm:ss&quot;,精确到日的形式 &quot;yyyy-MM-dd&quot;
	 * 
	 *   例如，将字符串&quot;2009-12-24 12:09:35&quot;转换成日期类型，则需要将参数strFormat置为
	 * &quot;yyyy-MM-dd HH:mm:ss&quot;形式，这样就能将其转换为日期类型的了。
	 * </pre>
	 * 
	 * @param strDate
	 *            - 需要转换的日期(字符串)
	 * @param strFormat
	 *            - 需要格式化日期(字符串)的格式
	 * @return - 日期
	 */
	public static Date string2Date(String strDate, String strFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			Date date = sdf.parse(strDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将"yyyy-MM-dd"格式的日期字符串转换为日期类型
	 * 
	 * @param strDate
	 *            - 需要转换的日期字符串
	 * @return - 日期类型
	 */
	public static Date string2Date(String strDate) {
		return string2Date(strDate, "yyyy-MM-dd");
	}

	/**
	 * 将日期转换成指定格式的字符串类型. 常用日期格式有:精确到秒的形式 "yyyy-MM-dd HH:mm:ss",精确到日的形式
	 * "yyyy-MM-dd"
	 * 
	 * @param date
	 *            - 需要转换的日期
	 * @param format
	 *            - 日期格式
	 * @return - 转换成的字符串
	 */
	public static final String date2String(Date date, String format) {
		if (date == null || format == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = sdf.format(date);
		return result;

	}

	/**
	 * 将日期转换成指定格式( "yyyy-MM-dd")的字符串类型.
	 * 
	 * @param date
	 *            - 需要转换的日期
	 * @return - 转换成的字符串
	 */
	public static final String date2String(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * 得到当前月的月初
	 * 
	 * @return
	 */
	public static String getMonthBegin() {
		int x = 0, y = 0;
		String strY = null;
		x = Calendar.getInstance().get(Calendar.YEAR);
		y = Calendar.getInstance().get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}

	/**
	 * 得到当前月的月末
	 * 
	 * @return
	 */
	public static String getMonthEnd() {
		int x = 0, y = 0;
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = Calendar.getInstance().get(Calendar.YEAR);
		y = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = isLeapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 得到上个月的月初
	 * 
	 * @author zxr
	 * @return
	 */
	public static String getPreMonthBegin() {
		int x = 0, y = 0;
		String strY = null;
		x = Calendar.getInstance().get(Calendar.YEAR);
		y = Calendar.getInstance().get(Calendar.MONTH);
		System.out.println("x=" + x);
		System.out.println("y=" + y);
		if (y == 0) {
			x = x - 1;
			y = 12;
		}
		System.out.println("x=" + x);
		System.out.println("y=" + y);
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		System.out.println("strY=" + strY);
		return x + "-" + strY + "-01";
	}

	/**
	 * 得到上个月的月末
	 * 
	 * @author zxr
	 * @return
	 */
	public static String getPreMonthEnd() {
		int x = 0, y = 0;
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = Calendar.getInstance().get(Calendar.YEAR);
		y = Calendar.getInstance().get(Calendar.MONTH);
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = isLeapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 判断当前年份是否为闰年
	 * 
	 * @author zxr
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		boolean leap;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			leap = true;
		} else {
			leap = false;
		}
		return leap;
	}

	/**
	 * 计算两个日期之间的秒差
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getBetweenMin(Date a, Date b) {
		long dayNumber = 0;
		// 1小时=60分钟=3600秒=3600000
		long mins = 1000L;
		// long day= 24L * 60L * 60L * 1000L;计算天数之差
		try {
			dayNumber = (a.getTime() - b.getTime()) / mins;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dayNumber < 0) {
			return 300;
		}
		return (int) dayNumber;
	}

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_TIME_MINUTES = "yyyy-MM-dd HH:mm";
	public static final String DEFAULT_TIME_NAME = "yyyy-MM-ddHHmmss";
	private static final String DATE_EL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))"
			+ "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
			+ "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|"
			+ "([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?"
			+ "((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])"
			+ "|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])"
			+ "|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])" + "((\\s)|(\\:([0-5]?[0-9])))))?$";

	/**
	 * 日期转换常用格式
	 * 
	 * @author sunhaiyang
	 */
	public enum Format {

		YYYYMMDD("yyyyMMdd"),

		YYYY_MM_DD("yyyy-MM-dd"),

		YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),

		DDMMYYYY("ddMMMyy");

		private final String value;

		Format(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}

	/**
	 * 把String类型的日期转换成Date类型
	 */
	public static Date parseDate(String date, String pattern) {
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换的方法
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date, String fmt) {
		if (null != date) {
			final SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * <B>功能简述</B><br>
	 * EBE AV 日期查询处理
	 * 
	 * @author xiaojingze
	 * @param dates
	 * @return String
	 */
	public static String dealDate(String dates) {
		String month = getMMM(parseDate(dates, Format.YYYY_MM_DD.value()));
		String day = dates.split("-")[2];
		return day + month;
	}

	/**
	 * 得到当前月的第一天
	 */
	public static Date getMonthDay(String f) {
		final Calendar calendar = Calendar.getInstance();
		if ("first".equals(f)) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		}

	}

	/**
	 * 得到去年的日期
	 * 
	 * @return
	 */
	public static Date getLastYear() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 得到制定时间的年，月，日 已int 类型返回
	 */
	public static int getDatePattern(Date date, Pattern p) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (p) {
		case year:
			return calendar.get(Calendar.YEAR);
		case month:
			return calendar.get(Calendar.MONTH) + 1;
		case day:
			return calendar.get(Calendar.DATE);
		case hour:
			return calendar.get(Calendar.HOUR);
		default:
			break;
		}
		return 0;
	}

	/**
	 * 匹配英文表示的月份
	 * 
	 * @param date
	 *            日期
	 */
	public static String getMMM(Date date) {
		final String mmm = "";
		final int month = getDatePattern(date, Pattern.month);
		switch (month) {
		case 1:
			return "JAN";
		case 2:
			return "FEB";
		case 3:
			return "MAR";
		case 4:
			return "APR";
		case 5:
			return "MAY";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AUG";
		case 9:
			return "SEP";
		case 10:
			return "OCT";
		case 11:
			return "NOV";
		case 12:
			return "DEC";
		default:
			break;
		}
		return mmm;
	}

	/**
	 * 
	 * <B>功能简述</B><br>
	 * 根据英文简拼得到 月份
	 * 
	 * @author sunhaiyang
	 * @param mmm
	 *            简拼
	 * @return 月份
	 */
	public static String getMonth(String mmm) {
		String m = null;
		if (mmm.equalsIgnoreCase("JAN")) {
			m = "01";
		} else if (mmm.equalsIgnoreCase("FEB")) {
			m = "02";
		} else if (mmm.equalsIgnoreCase("MAR")) {
			m = "03";
		} else if (mmm.equalsIgnoreCase("APR")) {
			m = "04";
		} else if (mmm.equalsIgnoreCase("MAY")) {
			m = "05";
		} else if (mmm.equalsIgnoreCase("JUN")) {
			m = "06";
		} else if (mmm.equalsIgnoreCase("JUL")) {
			m = "07";
		} else if (mmm.equalsIgnoreCase("AUG")) {
			m = "08";
		} else if (mmm.equalsIgnoreCase("SEP")) {
			m = "09";
		} else if (mmm.equalsIgnoreCase("OCT")) {
			m = "10";
		} else if (mmm.equalsIgnoreCase("NOV")) {
			m = "11";
		} else if (mmm.equalsIgnoreCase("DEC")) {
			m = "12";
		}
		return m;
	}

	/**
	 * 判断是否为时间类型
	 * 
	 * @param date
	 * @return
	 * @author sunhaiyang
	 * @date Dec 14, 2011 4:00:46 PM
	 * @comment
	 */
	public static boolean isDateType(String date) {
		final java.util.regex.Pattern p = java.util.regex.Pattern.compile(DATE_EL);
		final Matcher m = p.matcher(date);
		return m.matches();
	}

	/**
	 * 获取给定日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheFirstDayOfWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getADayOfWeek(cal, Calendar.MONDAY).getTime();
	}

	/**
	 * 获取给定日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheLastDayOfWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getADayOfWeek(cal, Calendar.SUNDAY).getTime();
	}

	/**
	 * 获取给定日期上一周的第一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheFirstDayOfPreWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
		return getADayOfWeek(cal, Calendar.MONDAY).getTime();
	}

	/**
	 * 获取给定日期上一周的最后一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheLastDayOfPreWeek(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
		return getADayOfWeek(cal, Calendar.SUNDAY).getTime();
	}

	/**
	 * 获取给定日期所在周的某一天
	 * 
	 * @param day
	 * @param dayOfWeek
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	private static Calendar getADayOfWeek(Calendar day, int dayOfWeek) {
		final int currDayOfWeek = day.get(Calendar.DAY_OF_WEEK);
		final int week = 7;
		if (currDayOfWeek == dayOfWeek) {
			return day;
		}
		int diffDay = dayOfWeek - currDayOfWeek;
		if (currDayOfWeek == Calendar.SUNDAY) {
			diffDay -= week;
		} else if (dayOfWeek == Calendar.SUNDAY) {
			diffDay += week;
		}
		day.add(Calendar.DATE, diffDay);
		return day;
	}

	/**
	 * 获取两个日期相差天数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static int getDiffDays(Date beginDate, Date endDate) {
		final Calendar cal1 = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();

		cal1.setTime(beginDate);
		cal2.setTime(endDate);

		cal1.set(Calendar.MILLISECOND, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.HOUR_OF_DAY, 0);

		cal2.set(Calendar.MILLISECOND, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR_OF_DAY, 0);

		final long begin = cal1.getTimeInMillis();
		final long end = cal2.getTimeInMillis();

		return (int) ((end - begin) / MILLIS_PER_DAY);
	}

	/**
	 * 获取给定日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheFirstDayOfMonth(Date date) {
		return truncate(date, Calendar.MONTH);
	}

	/**
	 * 获取给定日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheLastDayOfMonth(Date date) {
		return addDays(ceiling(date, Calendar.MONTH), -1);
	}

	/**
	 * 获取给定日期上一月的第一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheFirstDayOfPreMonth(Date date) {
		return truncate(addMonths(date, -1), Calendar.MONTH);
	}

	/**
	 * 获取给定日期上一月的最后一天
	 * 
	 * @param date
	 * @return
	 * 
	 * @author sunhaiyang
	 */
	public static Date getTheLastDayOfPreMonth(Date date) {
		return addDays(truncate(date, Calendar.MONTH), -1);
	}

	/**
	 * 获取当前时间的小时
	 * 
	 * @return
	 */
	public static Integer getCurrentHour() {
		final Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * <B>功能简述</B><br>
	 * 功能详细描述 获得当前日期
	 * 
	 * @author majianguo
	 * @return [返回类型说明]
	 * @see [相关类或方法]
	 */
	public static String getDate(String pattern) {
		return new DateTime().toString(pattern);
	}

	/**
	 * 
	 * @return
	 * @author majianguo
	 * @date 2013-6-8 下午5:54:39
	 * @comment 获得当前时间带时分秒
	 */
	public static String getDateTime() {
		return new DateTime().toString(DEFAULT_TIME);
	}

	/***
	 * <B>功能简述</B><br>
	 * 功能详细描述 获得当前时间
	 * 
	 * @author majianguo
	 * @return [返回类型说明]
	 * @see [相关类或方法]
	 */
	public static String getTime() {
		return new DateTime().toString(DEFAULT_TIME);
	}

	public static int getWeekOfDate(Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 
	 * <B>功能简述</B><br>
	 * 根据给定日期和需要减少天数，获取减少后的日期
	 * 
	 * @author sunhaiyang
	 * @param date
	 *            修改时间
	 * @param day
	 *            减少天数（正整数）
	 * @return [返回类型说明]
	 */
	public static Date getBeforeDate(Date date, long day) {
		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			long time = date.getTime() - (day * 24 * 60 * 60 * 1000);
			calendar.setTimeInMillis(time);
			return calendar.getTime();
		}
		return null;
	}

	public static Date getBeforeDateM(Date date, int month) {
		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, month);
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * 
	* @methods parseLong 
	* @description <p>将long类型转换为date类型</p> 
	* @date 2014年11月21日 上午10:35:01
	 */
	public static Date parseLong(Long dateTime){
		try {
			Date date = new Date(dateTime);
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <B>功能简述</B><br>
	 * 根据给定日期和需要增加天数，获取增加后的日期
	 * 
	 * @author sunhaiyang
	 * @param date
	 *            修改时间
	 * @param day
	 *            增加天数（正整数）
	 * @return [返回类型说明]
	 */
	public static Date getAfterDate(Date date, long day) {
		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			long time = date.getTime() + (day * 24 * 60 * 60 * 1000);
			calendar.setTimeInMillis(time);
			return calendar.getTime();
		}
		return null;
	}
	
	/** 
	    * 判断当前日期是星期几<br> 
	    * <br> 
	    * @param pTime 修要判断的时间<br> 
	    * @return dayForWeek 判断结果<br> 
	    * @Exception 发生异常<br> 
	    */  
	public static int dayForWeek(String pTime) throws Exception {  
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();  
		c.setTime(sdf.parse(pTime));
		int dayForWeek = 0;  
		if(c.get(Calendar.DAY_OF_WEEK) == 1){  
			dayForWeek = 7;  
		}else{  
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		}  
		return dayForWeek;  
	}
	
	/**
	 * 日期转换的方法
	 * 本年内的，不显示年份；非本年内的，在日期之前显示年份（如2015年10月30日 周四 18:30）
	 * @param date
	 * @return String
	 * @throws Exception 
	 */
	public static String formatDateChina(Date date) throws Exception {
		if (null != date) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			final String dateString = sdf.format(date);
			final String dateNew = sdf.format(new Date());
			int number = dayForWeek(dateString);
			String dateStringYear = dateString.split("-")[0];
			String dateNewYear = dateNew.split("-")[0];
			String finish = "";
			if (!dateStringYear.equals(dateNewYear)) {
				finish = dateStringYear + "年";
			}
			finish = finish + dateString.split("-")[1] + "月" + dateString.split("-")[2].split(" ")[0] + "日";
			String week = "";
			switch (number) {
			case 1:
				week = "周一";
				break;
			case 2:
				week = "周二";
				break;
			case 3:
				week = "周三";
				break;
			case 4:
				week = "周四";
				break;
			case 5:
				week = "周五";
				break;
			case 6:
				week = "周六";
				break;
			case 7:
				week = "周日";
				break;
			default:
				break;
			}
			finish = finish + " " + week + " " + dateString.split("-")[2].split(" ")[1];
			return finish;
		}
		return "";
	}
	
	/**
	 * 日期转换的方法:
	 * 今天（0点-24:00）之内的，显示到分钟，如：14:30
	 * 今天之前到本周一零点之间的，显示周几，如：周一
	 * 本周之前，显示日期，如：10月30日
	 * 本年内的，不显示年份；非本年内的，在日期之前显示年份（如2015年10月30日 周四 18:30）；
	 * @param date
	 */
	public static String formatDateOther(Date date) throws Exception {
		if (null != date) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			final String dateString = sdf.format(date);
			final String dateNew = sdf.format(new Date());
			String dateStringYear = dateString.split("-")[0];
			String dateNewYear = dateNew.split("-")[0];
			//今天的开始
			Date today = getNowdayBegin(new Date());
			//本周开始
			Date week = getTheFirstDayOfWeek(today);
			if (date.getTime() > today.getTime()) {
				return dateString.split(" ")[1];
			} else if (date.getTime() > week.getTime()) {
				int number = dayForWeek(dateString);
				String tmp = "";
				switch (number) {
				case 1:
					tmp = "周一";
					break;
				case 2:
					tmp = "周二";
					break;
				case 3:
					tmp = "周三";
					break;
				case 4:
					tmp = "周四";
					break;
				case 5:
					tmp = "周五";
					break;
				case 6:
					tmp = "周六";
					break;
				case 7:
					tmp = "周日";
					break;
				default:
					break;
				}
				return tmp;
			} else {
				if (dateStringYear.equals(dateNewYear)) {
					return dateString.split("-")[1] + "月" + dateString.split("-")[2].split(" ")[0] + "日";
				} else {
					return dateStringYear + "年" + dateString.split("-")[1] + "月" + dateString.split("-")[2].split(" ")[0] + "日";
				}
			}
		}
		return "";
	}
	
	/**
	 * 得到时间的前number天。
	 */
	public static Date getUpDateNumber(Date date, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -number);
		return calendar.getTime();
	}
	
	
	
	public static void main(String[] args) throws Exception {
//		long d = new Date().getTime();
//		long dd = parseDate("2015-01-12 13:02:49").getTime();
//		long l= 1418729408000L;//1416623040573
//		long l2= 1419671040506L;
//		Date date = new Date(l);
//		Date date2 = new Date(l2);

//		System.out.println(formatDateChina(new Date()));
		String begin = "2015-04-01 00:00:00";
		String end = "2015-02-28 00:00:00";
		List<String> list = getDateMonth(begin, end, false);
		System.out.println("--------------"+list.size());
		
	}
	
	public static String getDate() {
		return new DateTime().toString(DEFAULT_PATTERN);
	}
}