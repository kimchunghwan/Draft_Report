package showCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import tool.ReportProc;
import tool.jodConverter;

public class Report_showcase {

	private static Connection conn = null;
	private static PreparedStatement preStatement = null;
	static LinkedHashMap<String, String> lstData = null;
	public static ArrayList<LinkedHashMap<String, String>> DBData = null;

	public static void main(String[] args) throws Exception {

		String exportPath = "";
		String reportInfoPath = "";
		jodConverter jodconverter = new jodConverter();

		// PROFORMA INVOICE_PDF
		reportInfoPath = System.getProperty("user.dir")
				+ "\\reportInfo\\reportInfo_tmple_PROFORMA INVOICE_PDF.xml";
		// make Dummy instead of Database
		DBData = Report_DummyData.mkDummyData_PROFORMA(20);

		exportPath = ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		System.out.println("export : " + exportPath);

		// sagawa
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_tmpl_sagawa.xml";

		DBData = Report_DummyData.mkDummyData_sagawa(5);

		exportPath = ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		System.out.println("export : " + exportPath);

		// 合計請求書
		reportInfoPath = System.getProperty("user.dir")
				+ "\\reportInfo\\reportInfo_tmpl_合計請求書_329N.xml";

		DBData = Report_DummyData.mkDummyData_329N(5);

		exportPath = ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		System.out.println("export : " + exportPath);

		// torihiki
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_tmpl_取引会社請求書.xml";

		DBData = Report_DummyData.mkDummyData_torihiki(18);

		exportPath = ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		System.out.println("export : " + exportPath);

		// sample
		reportInfoPath = System.getProperty("user.dir") + "\\reportInfo\\reportInfo_tmpl_sample.xml";

		DBData = Report_DummyData.mkDummyData(5);

		exportPath = ReportProc.exportReport(DBData, ReportProc.initReportDataXML(reportInfoPath), jodconverter);

		System.out.println("export : " + exportPath);
	}

	private static void connDB() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.12:1521:AIM", "ZSYSAIM", "we$2016");
		conn.setAutoCommit(false);
		System.out.println("DB connection : OK");
	}

}
