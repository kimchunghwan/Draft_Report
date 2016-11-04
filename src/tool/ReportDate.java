package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportDate {

	public static final String FORMAT_DATE = "yyyy/MM/dd";

	public static final String FORMAT_DATETIME = "yyyy/MM/dd HH:mm:ss";

	public static final String DATE_MIN = "1900/01/01";

	public static final String DATE_MAX = "9999/01/01";

	public static Calendar getSysCalendar() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	public static String getSysDate() {
		return getSysDateTime(ReportDate.FORMAT_DATE);
	}

	public static String getSysDateTime() {
		return getSysDateTime(ReportDate.FORMAT_DATETIME);
	}

	public static String getSysDateTime(String formatStr) {
		Calendar cal = getSysCalendar();
		return toString(cal, formatStr);
	}

	public static Calendar toCalendar(String dateStr) throws ParseException {
		return toCalendar(dateStr, ReportDate.FORMAT_DATE);
	}

	public static Calendar toCalendar(String dateStr, String formatStr) throws ParseException {
		SimpleDateFormat sdt = new SimpleDateFormat(formatStr);
		Date date = sdt.parse(dateStr);
		Calendar cal = getSysCalendar();
		cal.setTime(date);
		return cal;
	}

	public static String toString(Calendar cal) {
		return toString(cal, FORMAT_DATE);
	}

	public static String toString(Calendar cal, String formatStr) {
		SimpleDateFormat sdt = new SimpleDateFormat(formatStr);
		return sdt.format(cal.getTime());
	}

	public static int compareTo(String fromDate, String toDate) {
		if (commonUtil.isNullOrEmpty(fromDate) == true) {
			fromDate = ReportDate.DATE_MIN;
		}

		if (commonUtil.isNullOrEmpty(toDate) == true) {
			toDate = ReportDate.DATE_MAX;
		}
		int result = fromDate.compareTo(toDate);
		return result;
	}
}
