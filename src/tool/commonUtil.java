package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class commonUtil {

	public static final String STR_EMPTY = "";

	public static final String STR_NEWLINE = System.getProperty("line.separator");

	private static final String UTF8_BOM = "\uFEFF";

	public static final Gson gson = new GsonBuilder().serializeNulls().create();

	public static final int INT_ONE = 1;

	public static final int INT_ZERO = 0;

	// コンソール出力（サーバーデバッグ用）
	// PRM
	// prmMsg:出力する内容
	public static void print(Object msg) {
		System.out.println(msg.toString());
	}

	public static boolean isPatternString(String inputString, String pattern) {
		boolean isPattern = false;

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(inputString);
		isPattern = m.find();

		return isPattern;
	}

	// START 140109 Added by KJ.Lee Utilメソッド
	public static boolean isNullOrEmpty(String value) {
		if (value == null) {
			return true;
		}
		if ("".equals(value)) {
			return true;
		}
		return false;
	}

	// *********************************************************************
	// ■関数
	// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// *********************************************************************
	// オブジェクト→文字列変換（JSONで取得したリストオブジェクト）
	// PRM prmStringObject:文字列オブジェクト（JSON）
	// PRM rtnString:文字列（c#）
	public static String toString(Object stringObject) {
		return toString(stringObject, "");
	}

	public static String toString(Object stringObject, String value2) {
		if (stringObject != null) {
			return stringObject.toString();
		} else {
			return value2;
		}
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#）
	public static int toInt(Object prmIntObject) {
		int rtnInt = 0;
		if (prmIntObject != null) {
			rtnInt = Integer.parseInt(prmIntObject.toString());
		}
		return rtnInt;
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#）
	public static short toShort(Object prmIntObject) {
		short rtnInt16 = 0;
		if (prmIntObject != null) {
			if (prmIntObject.toString() != "") {
				rtnInt16 = Short.parseShort(prmIntObject.toString());
			}
		}
		return rtnInt16;
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#）
	public static int toInt32(Object prmIntObject) {
		return toInt(prmIntObject);
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt64:数字（c#）
	public static long toInt64(Object prmIntObject) {
		long rtnInt64 = 0;
		if (prmIntObject != null) {
			if (commonUtil.toString(prmIntObject) != "") {
				rtnInt64 = Long.parseLong(prmIntObject.toString());
			}
		}
		return rtnInt64;
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnDouble:数字（c#）
	public static double toDouble(Object prmIntObject) {
		double rtnDouble = 0;
		if (prmIntObject != null) {
			if (prmIntObject.toString() != "") {
				rtnDouble = Double.parseDouble(prmIntObject.toString());
			}
		}
		return rtnDouble;
	}

	// 1 -> 0001
	public static String getFixedString(int num, int length) {
		String str = "" + num;
		for (int i = str.length(); i < length; i++) {
			str = '0' + str;
		}
		return str;
	}

	public static String getStringDatetime(Calendar cal) {
		return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(cal.getTime());
	}

	public static String getStringDate(Calendar cal) {
		return new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime());
	}

	public static String getStringTime(Calendar cal) {
		return new SimpleDateFormat("HH:mm:ss").format(cal.getTime());
	}

	public static String getStringYear(Calendar cal) {
		return new SimpleDateFormat("YYYY").format(cal.getTime());
	}

	public static String getStringMonth(Calendar cal) {
		return new SimpleDateFormat("MM").format(cal.getTime());
	}

	public static String getStringDay(Calendar cal) {
		return new SimpleDateFormat("dd").format(cal.getTime());
	}

	public static String getStringDatetimeForFile(Calendar cal) {
		return new SimpleDateFormat("YYYYMMddHHmmssSSS").format(cal.getTime());
	}

	public static String getStringDatetimeForFile(Date date) {
		return new SimpleDateFormat("YYYYMMddHHmmssSSS").format(date.getTime());
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#）
	public static Calendar getDatetime(String prmStringDateTime) {
		Calendar rtnDateTime = null;
		String dtYear = "";
		String dtMonth = "";
		String dtDate = "";
		String dtHour = "";
		String dtMinute = "";
		String dtSecond = "";
		if (prmStringDateTime.length() == 10) {
			rtnDateTime = Calendar.getInstance();
			dtYear = prmStringDateTime.substring(0, 4);
			dtMonth = prmStringDateTime.substring(5, 7);
			dtDate = prmStringDateTime.substring(8, 10);
			rtnDateTime.set(toInt(dtYear), toInt(dtMonth) - 1, toInt(dtDate), 0, 0,
					0);
		} else if (prmStringDateTime.length() == 19) {
			rtnDateTime = Calendar.getInstance();
			dtYear = prmStringDateTime.substring(0, 4);
			dtMonth = prmStringDateTime.substring(5, 2);
			dtDate = prmStringDateTime.substring(8, 2);
			dtHour = prmStringDateTime.substring(11, 2);
			dtMinute = prmStringDateTime.substring(14, 2);
			dtSecond = prmStringDateTime.substring(17, 2);
			rtnDateTime.set(toInt(dtYear), toInt(dtMonth) - 1, toInt(dtDate),
					toInt(dtHour), toInt(dtMinute), toInt(dtSecond));
		}
		return rtnDateTime;
	}

	// オブジェクト→リスト変換（JSONで取得したリストオブジェクト）
	// PRM prmListObject:リスト（JSON）
	// PRM rtnList:リスト（c#）
	public static ArrayList<LinkedHashMap<String, String>> toList(
			Object prmListObject) {
		ArrayList<LinkedHashMap<String, String>> rtnList = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> rtnDict = null;
		ArrayList<LinkedTreeMap<String, Object>> rtnObjectList = toObjectList(
				prmListObject);
		for (int i = 0; i < rtnObjectList.size(); i++) {
			Map<String, Object> rtbObjectDict = rtnObjectList.get(i);
			rtnDict = new LinkedHashMap<String, String>();
			for (Entry<String, Object> entry : rtbObjectDict.entrySet()) {
				rtnDict.put(entry.getKey(), toString(entry.getValue()));
			}
			rtnList.add(rtnDict);
		}
		return rtnList;
	}

	// オブジェクト→リスト変換（JSONで取得したリストオブジェクト）
	// PRM prmListObject:リスト（JSON）
	// PRM rtnList:リスト（c#）
	@SuppressWarnings({"unchecked", "rawtypes"})
	static ArrayList<LinkedTreeMap<String, Object>> toObjectList(
			Object prmListObject) {
		ArrayList<LinkedTreeMap<String, Object>> rtnList = null;
		if (prmListObject instanceof Iterable) {
			rtnList = new ArrayList<LinkedTreeMap<String, Object>>();
			Iterator<Object> enumerator = ((Iterable) prmListObject).iterator();
			while (enumerator.hasNext()) {
				rtnList.add((LinkedTreeMap<String, Object>) enumerator.next());
			}
		}
		return rtnList;
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#
	public static ArrayList<LinkedHashMap<String, String>> getYearList(
			Calendar currDate) {
		int prmBeforeCnt = 5;
		int prmAfterCnt = 5;
		return getYearList(currDate, prmBeforeCnt, prmAfterCnt);
	}

	// TODO: currDate = WeSess.getCurrDate();
	@SuppressWarnings("serial")
	public static ArrayList<LinkedHashMap<String, String>> getYearList(
			Calendar currDate, int prmBeforeCnt, int prmAfterCnt) {
		ArrayList<LinkedHashMap<String, String>> rtnYearList = new ArrayList<LinkedHashMap<String, String>>();
		Calendar startDate = (Calendar) currDate.clone();
		startDate.add(Calendar.YEAR, -1 * prmBeforeCnt);
		int startYear = startDate.get(Calendar.YEAR);

		Calendar endDate = (Calendar) currDate.clone();
		endDate.add(Calendar.YEAR, prmAfterCnt);
		int endYear = endDate.get(Calendar.YEAR);
		for (int i = startYear; i <= endYear; i++) {
			final String year = Integer.toString(i);
			rtnYearList.add(new LinkedHashMap<String, String>() {
				{
					put("label", year);
					put("value", year);
				}
			});
		}
		return rtnYearList;
	}

	// オブジェクト→数字（JSONで取得したリストオブジェクト）
	// PRM prmIntObject:文字列オブジェクト（JSON）
	// PRM rtnInt:数字（c#）
	@SuppressWarnings("serial")
	public static ArrayList<LinkedHashMap<String, String>> getMonthList() {
		ArrayList<LinkedHashMap<String, String>> rtnYearList = new ArrayList<LinkedHashMap<String, String>>();
		for (int i = 1; i <= 12; i++) {
			final String iStr = Integer.toString(i);
			rtnYearList.add(new LinkedHashMap<String, String>() {
				{
					put("label", iStr);
					put("value", iStr.length() == 2 ? iStr : "0" + iStr);
				}
			});
		}
		return rtnYearList;
	}

	public static Boolean copyFile(String sourcePath, String targetPath)
			throws Exception {

		FileInputStream is = new FileInputStream(sourcePath);
		FileOutputStream out = new FileOutputStream(targetPath);

		byte[] buffer = new byte[1024];

		int length;

		while ((length = is.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}

		is.close();
		out.close();

		return true;
	}

	public static void deleteFile(String path) throws IOException {
		Files.delete(new File(path).toPath());
	}

	public static boolean existFile(String path) throws IOException {
		File file = new File(path);
		return file.exists() && file.getCanonicalPath().equals(path);
	}

	public static String readFile(String path) throws IOException {
		return readFile(path, Charset.forName("UTF-8"));
	}

	public static String readFile(String path, Charset charset)
			throws IOException {
		StringBuilder content = new StringBuilder();
		List<String> allLines = Files.readAllLines(new File(path).toPath(),
				charset);
		for (String someLine : allLines) {
			content.append(someLine);
			content.append(STR_NEWLINE);
		}
		return content.toString().replaceFirst(UTF8_BOM, STR_EMPTY);
	}

	public static Boolean renameFile(String sourcePath, String targetPath)
			throws IOException {
		File oldFile = new File(sourcePath);
		File newFile = new File(targetPath);
		if (oldFile.isFile()) {
			oldFile.renameTo(newFile);
			return true;
		} else {
			return false;
		}
	}

	public static Boolean writeFile(String path) throws IOException {
		return writeFile(path, null);
	}

	public static Boolean writeFile(String path, String content)
			throws IOException {
		return writeFile(path, content, Charset.forName("UTF-8"));
	}

	public static Boolean writeFile(String path, String content, Charset charset)
			throws IOException {
		Writer writer = null;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(path), charset));
			if (content != null) {
				writer.write(content);
			}
		} catch (IOException e) {
			// TODO
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				// TODO
				return false;
			}
		}
		return true;
	}

	// END 140109 Added by KJ.Lee Utilメソッド

	public static LinkedHashMap<String, String> getFirstItem(
			ArrayList<LinkedHashMap<String, String>> list) {
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public static String getAllException(Throwable e) {
		StackTraceElement[] errList = e.getStackTrace();
		StringBuilder errMsg = new StringBuilder();
		errMsg.append(e.getClass());
		errMsg.append(" : ");
		errMsg.append(e.getMessage());
		errMsg.append("\r\n");
		for (StackTraceElement stackTraceElement : errList) {
			errMsg.append(stackTraceElement.toString());
			errMsg.append("\r\n");
		}
		return errMsg.toString();
	}

	public static String getDababaseCommonDataType(String originalDataType) {
		switch (originalDataType) {
			case "bigint" : // MySQL SQLServer
			case "bit" : // MySQL SQLServer
			case "decimal" :// MySQL SQLServer
			case "double" :// MySQL
			case "float" :// MySQL SQLServer
			case "int" :// MySQL SQLServer
			case "mediumint" :// MySQL
			case "money" : // SQLServer
			case "numeric" :// SQLServer
			case "real" :// SQLServer
			case "smallint" :// MySQL SQLServer
			case "smallmoney" : // SQLServer
			case "tinyint" :// MySQL SQLServer
				return "numeric";
			case "binary" :// MySQL SQLServer
			case "blob" :// MySQL
			case "char" : // MySQL SQLServer
			case "enum" :// MySQL
			case "longblob" :// MySQL
			case "longtext" :// MySQL
			case "mediumblob" : // MySQL
			case "mediumtext" :// MySQL
			case "nchar" :// SQLServer
			case "ntext" :// SQLServer
			case "nvarchar" :// SQLServer
			case "set" :// MySQL
			case "text" :// MySQL SQLServer
			case "tinyblob" :// MySQL
			case "tinytext" : // MySQL
			case "varbinary" :// MySQL SQLServer
			case "varchar" : // MySQL SQLServer
				return "string";
			case "date" :// MySQL SQLServer
			case "datetime" :// MySQL SQLServer
			case "datetime2" :// SQLServer
			case "datetimeoffset" :// SQLServer
			case "smalldatetime" :// SQLServer
			case "time" :// MySQL SQLServer
			case "timestamp" :// MySQL
			case "year" :// MySQL
				return "datetime";
			default :
				return null;
		}
	}

	public static String getTempFolder() {

		File tempFolder = new File(System.getProperty("user.dir") + "\\temp");
		if (!tempFolder.exists()) {
			tempFolder.mkdirs();
		}
		return tempFolder.getPath() + File.separator;
	}

}
