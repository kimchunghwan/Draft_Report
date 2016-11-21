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

	public static void main(String[] args) throws Exception {


		jodConverter jodconverter = new jodConverter();

		for (String reportInfo : lstReportInfo) {

			// test PDF
			String reportInfoPath = System.getProperty("user.dir") + reportInfo;
			// make Dummy instead of Database
			DBData = DummyData.mkDummyData(20);

			ReportProc.exportReport(DBData, ReportProc.initReportData(reportInfoPath), jodconverter);

		}

		// test PDF
		String reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_templete_PDF.json";
		// make Dummy instead of Database
		DBData = DummyData.mkDummyData(20);

		ReportProc.exportReport(DBData, ReportProc.initReportData(reportInfoPath), jodconverter);

		// test Excel
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_templete_EXCEL.json";
		DBData = DummyData.mkDummyData(20);

		ReportProc.exportReport(DBData, ReportProc.initReportData(reportInfoPath), jodconverter);

		// test send mail
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_templete_MAIL.json";

		DBData = DummyData.mkDummyData(5);

		ReportProc.exportReport(DBData, ReportProc.initReportData(reportInfoPath), jodconverter);

		// sagawa
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_tmpl_sagawa.xml";

		DBData = DummyData.mkDummyData(5);

		ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		// torihiki
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_tmpl_取引会社請求書.xml";

		DBData = DummyData.mkDummyData(5);

		ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

	}


	private static void connDB() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.12:1521:AIM", "ZSYSAIM", "we$2016");
		conn.setAutoCommit(false);
		System.out.println("DB connection : OK");
	}

}
