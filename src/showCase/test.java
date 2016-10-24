package showCase;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import tool.ReportData;
import tool.ReportProc;

public class test {

	public static ArrayList<LinkedHashMap<String, String>> DBData = null;

	@Test
	public void test() throws Exception {

		// test PDF
		String reportInfoPath = "";

		DBData = WE_Report.mkDummyData(5);

		reportInfoPath = System.getProperty("user.dir")+"\\reportInfo\\reportInfo_templete_MAIL.json";

		ReportData rd = ReportProc.initReportData(reportInfoPath);

		System.out.println(ReportProc.exportReport(DBData, rd));


	}



}
