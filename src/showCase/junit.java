package showCase;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.google.gson.GsonBuilder;

import tool.ReportData;

public class junit {

	static String[] arrXmlPath = { "F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_TEST.xml",
			"F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_EXCEL.xml",
			"F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_PDF.xml"

	};

	@Test
	public void test() throws IOException {
		for (String str : arrXmlPath) {

			ReportData rd = JAXB.unmarshal(str, ReportData.class);
			Writer jw = new FileWriter(str.replace(".xml", ".json"));
			(new GsonBuilder()).create().toJson(rd, jw);
			System.out.println(jw.toString());
			jw.close();


		}
	}

}
