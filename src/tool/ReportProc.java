package tool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Timer;

import javax.mail.Message;
import javax.mail.Transport;
import javax.xml.bind.JAXB;

import org.apache.poi.hpsf.MarkUnsupportedException;

public class ReportProc {

	public static final int REPORT_TYPE_PDF = 0;
	public static final int REPORT_TYPE_EXCEL = 1;
	public static final int REPORT_TYPE_EMAIL = 2;
	private static final int REPORT_TYPE_CSV = 4;
	private static final String MAIL_HTML_UTF8 = "text/html; charset=utf-8";

	/***
	 * データベースの取得結果をレポート用のデータに変換
	 *
	 * @param reportData
	 * @param arrData2
	 */
	public static void convert2RD(ReportData reportData, ArrayList<LinkedHashMap<String, String>> arrData2) {

		for (String key : reportData.items.keySet()) {

			ArrayList<String> arrValue = new ArrayList<>();

			for (LinkedHashMap<String, String> map : arrData2) {
				if (!reportData.items.get(key).isLst && arrValue.size() > 0) {
					break;
				}
				arrValue.add(map.get(key));
			}
			reportData.getItem(key).lstValue = arrValue;
		}

	}

	public static String exportReport(ArrayList<LinkedHashMap<String, String>> dBData, String reportInfoPath)
			throws Exception {
		String exportFilePath = null;

		// Load Report Info
		ReportData reportData = JAXB.unmarshal(reportInfoPath, ReportData.class);

		// Data init for Report
		convert2RD(reportData, dBData);

		switch (reportData.reportType) {

		case REPORT_TYPE_PDF:

			exportFilePath = exportFile(reportData, ".pdf");

			break;

		case REPORT_TYPE_EXCEL:
			exportFilePath = ReportExcel.exportExcel(reportData);
			break;

		case REPORT_TYPE_EMAIL:
			// mail setting for send
			Message msg = ReportMail.init(reportData);

			// make Contents
			exportFilePath = exportFile(reportData, ".html");

			InputStream in = new FileInputStream(exportFilePath);
			System.out.println(in.toString());
			// set Contents
			msg.setSubject("Testing Subject");

			msg.setContent(WeFile.readFile(exportFilePath, WeFile.CHARSET_UTF8), MAIL_HTML_UTF8);
			msg.setSentDate(new Date());

			// send mail
			Transport.send(msg);
			System.out.println("send mail success!");

			break;

		case REPORT_TYPE_CSV:
			break;

		default:
			System.out.println("not defined reportType! : " + reportData.reportType);
			break;
		}

		return exportFilePath;

	}

	private static String exportFile(ReportData reportData, String mime) throws Exception {

		String tempExcelPath = ReportExcel.exportExcel(reportData);
		String exportFilePath = reportData.getExportFolder() + reportData.exportFileNM + mime;
		jodConverter.convertFile(tempExcelPath, exportFilePath);

		// tempFile delete
		commonUtil.deleteFile(tempExcelPath);

		return exportFilePath;
	}

}
