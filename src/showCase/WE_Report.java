package showCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import tool.ReportData;
import tool.ReportProc;
import tool.jodConverter;

public class WE_Report {

	private static Connection conn = null;
	private static PreparedStatement preStatement = null;
	static LinkedHashMap<String, String> lstData = null;
	public static ArrayList<LinkedHashMap<String, String>> DBData = null;

	static String[] lstReportInfo = {

			"\\reportInfo\\reportInfo_templete_PDF.json", "\\reportInfo\\reportInfo_templete_EXCEL.json",
			"\\reportInfo\\reportInfo_templete_MAIL.json" };

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData(int maxCnt) {

		ArrayList<LinkedHashMap<String, String>> lstMap = new ArrayList<>();

		for (int idx = 0; idx < maxCnt; idx++) {
			LinkedHashMap<String, String> imap = new LinkedHashMap<>();

			imap.put("Index", String.valueOf(idx));
			imap.put("NAME01", "jackson" + "_" + String.valueOf(idx));
			imap.put("ADDR01", "taito-ku");
			imap.put("ADDR02", "tokyo");
			imap.put("NATION01", "japan");
			imap.put("TEL01", "03-4123-5123");
			imap.put("ZIP01", "600-0030");
			imap.put("NAME02", "carry");
			imap.put("ADDR03", "sakae-tyou");
			imap.put("ADDR04", "nagoya");
			imap.put("NATION02", "japan");
			imap.put("TEL01", "000-0000-0000");
			imap.put("ZIP01", "000-0000");
			imap.put("LST_DSCRPT", "partNo" + "_" + String.valueOf(idx));
			imap.put("LST_QNTT", "99999" + String.valueOf(idx));
			imap.put("LST_UPRC", "999999999" + String.valueOf(idx));
			imap.put("LST_TAMT", "9999999999" + String.valueOf(idx));
			imap.put("TCNT", "99999");
			imap.put("TAMT", "999999999");
			imap.put("BARCODE_ean-13", "123456789012");
			imap.put("BARCODE_itf-14", "1234567890123");
			imap.put("BARCODE_postnet", "123456789012");
			imap.put("BARCODE_code39", "123456789012");
			imap.put("BARCODE_codabar", "123456789012");
			imap.put("BARCODE_royal-mail-cbc", "123456789012");
			imap.put("BARCODE_pdf417", "123456789012");
			imap.put("BARCODE_upc-a", "123456789012");
			imap.put("BARCODE_upc-e", "1234567");
			imap.put("BARCODE_usps4cb", "12345678901234567890");
			imap.put("LOGO", "F:\\Workspace\\WE_Report\\source\\logo.png");
			imap.put("ADDR_TO", "chunghkim@kissco.co.jp");
			imap.put("ADDR_CC", "chunghkim@kissco.co.jp");
			imap.put("ADDR_BCC", "chunghkim@kissco.co.jp");
			imap.put("ADDR_FROM", "chunghkim@kissco.co.jp");
			imap.put("BARCODE_usps4cb", "12345678901234567890");

			lstMap.add(imap);
		}

		return lstMap;

	}

	public static void main(String[] args) throws Exception {

		try {
			// // check request
			// connDB();
			//
			// // read data for request : select SQL file
			// String sql = "select sysdate , sysdate+3 , sysdate+1 from dual";
			// getData(sql);
			// conn.close();
		} catch (Exception e) {
			setDataiferror();
			System.out.println("ERRRR");
		}
		jodConverter jodconverter = new jodConverter();

		for (String reportInfo : lstReportInfo) {

			// test PDF
			String reportInfoPath = System.getProperty("user.dir") + reportInfo;
			// make Dummy instead of Database
			DBData = mkDummyData(20);

			ReportProc.exportReport(DBData, ReportProc.initReportData(reportInfoPath), jodconverter);

		}

		// // test PDF
		// String reportInfoPath = System.getProperty("user.dir") +
		// "\\reportInfo\\reportInfo_templete_PDF.json";
		// // make Dummy instead of Database
		// DBData = mkDummyData(20);
		//
		// ReportProc.exportReport(DBData,
		// ReportProc.initReportData(reportInfoPath), jodconverter);
		//
		// // test Excel
		// reportInfoPath = System.getProperty("user.dir") +
		// "\\reportInfo\\reportInfo_templete_EXCEL.json";
		// DBData = WE_Report.mkDummyData(20);
		//
		// ReportProc.exportReport(DBData,
		// ReportProc.initReportData(reportInfoPath), jodconverter);
		//
		// // test send mail
		// reportInfoPath = System.getProperty("user.dir") +
		// "\\reportInfo\\reportInfo_templete_MAIL.json";
		//
		// DBData = WE_Report.mkDummyData(5);
		//
		// ReportProc.exportReport(DBData,
		// ReportProc.initReportData(reportInfoPath), jodconverter);

	}

	private static void setDataiferror() {
		// TODO Auto-generated method stub
		lstData = new LinkedHashMap<String, String>();
		lstData.put("sysdate", " 2016-10-06 16:16:20");
		lstData.put("sysdate+3", " 2016-10-09 16:16:20");
		lstData.put("sysdate+1", " 2016-10-07 16:16:20");
	}

	private static void connDB() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.12:1521:AIM", "ZSYSAIM", "we$2016");
		conn.setAutoCommit(false);
		System.out.println("DB connection : OK");
	}

}
