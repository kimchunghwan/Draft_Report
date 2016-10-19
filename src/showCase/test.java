package showCase;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.junit.Test;

import tool.ReportProc;

public class test {

	public static ArrayList<LinkedHashMap<String, String>> DBData = null;

	@Test
	public void test() throws Exception {
		// TODO insert row

		// test PDF
		String reportInfoPath = System.getProperty("user.dir")+"\\reportInfo\\reportInfo_templete_MAIL.xml";

		System.out.println('-');
		DBData = WE_Report.mkDummyData(5);
		//


		System.out.println();

		System.out.println(ReportProc.exportReport(DBData, reportInfoPath));

	}

}
