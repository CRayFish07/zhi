package tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static long getCurDate() {
		return new java.util.Date().getTime();
	}

	public static String getCurDate(String partten) {
		SimpleDateFormat format = new SimpleDateFormat(partten);
		return format.format(new java.util.Date());
	}

	public static java.sql.Date converterToSqldate(String sdate, String partten) {
		SimpleDateFormat format = new SimpleDateFormat(partten);
		try {
			java.util.Date date = format.parse(sdate);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Timestamp converterToSqlDateTime(String sdate, String partten) {
		SimpleDateFormat format = new SimpleDateFormat(partten);
		try {
			java.util.Date date = format.parse(sdate);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSectionFormatDate(long dateValue) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd EEEE");
		java.util.Date date = new java.util.Date(dateValue);
		return format.format(date);
	}

	public static String formatSqlDate(java.sql.Date sqlDate, String partten) {
		if (sqlDate == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(partten);
		java.util.Date date = new java.util.Date(sqlDate.getTime());
		return format.format(date);
	}

	public static String formatDate(java.util.Date date, String partten) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(partten);
		return format.format(date);
	}

	public static long addDay(long curDate, int days) {
		java.util.Date date = new java.util.Date(curDate);
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(5, days);
		return calender.getTime().getTime();
	}

	public static long getDaysBetweenTimes(long date) {
		return (getCurDate() - date) / 1000L / 86400L;
	}

	public static long getHoursBetweenTimes(long date) {
		return (getCurDate() - date) / 1000L / 3600L;
	}

	public static long buildMsgId() {
		return 0L;
	}
}
