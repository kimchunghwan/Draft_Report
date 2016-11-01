package showCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXB;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.star.io.IOException;

import tool.ReportData;
import tool.ReportProc;
import tool.Zxing;
import tool.jodConverter;

public class test {

	public static ArrayList<LinkedHashMap<String, String>> DBData = null;

	static String[] arrXmlPath = { "F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_TEST.xml",
			"F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_EXCEL.xml",
			"F:\\05_Java\\workspace\\WE_Report_github\\reportInfo\\reportInfo_templete_PDF.xml"

	};

	@SuppressWarnings("deprecation")
	public static void createQRCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight,
			int qrCodewidth) throws Exception {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap) throws Exception {
		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
		return qrCodeResult.getText();
	}

	@Test
	public void testQRcode() throws Exception {
		String qrCodeData = "Hello World!";
		String filePath = "QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		Zxing.createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
		System.out.println("QR Code image created successfully!");

		System.out.println("Data read from QR Code: " + Zxing.readQRCode(filePath, charset, hintMap));
	}

	@Test
	public void test() throws IOException, java.io.IOException {
		for (String str : arrXmlPath) {

			ReportData rd = JAXB.unmarshal(str, ReportData.class);
			Writer jw = new FileWriter(str.replace(".xml", ".json"));
			(new GsonBuilder()).create().toJson(rd, jw);
			System.out.println(jw.toString());
			jw.close();

		}
	}

	@Test
	public void testJodconverter() {
		jodConverter jcon = new jodConverter();

		jcon.convertFile("F:\\05_Java\\workspace\\WE_Report_github\\export\\templete01_Excel_result_testjod.xls",
				"F:\\05_Java\\workspace\\WE_Report_github\\export\\templete01_Excel_result_testjod_convert.pdf",
				"F:\\05_Java\\workspace\\WE_Report_github\\export\\");


	}

}
