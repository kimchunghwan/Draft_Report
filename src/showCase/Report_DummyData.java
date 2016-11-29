package showCase;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Report_DummyData {

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData_PROFORMA(int maxCnt) {

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
			imap.put("SHIPPED_PER", "Test code ");
			imap.put("FINAL_DESTINATION", "AAAA TEST");
			imap.put("PORT_OF_LOADING", "TEST AAAA");

			imap.put("MAIL_ITEM_NO", "MAIL_ITEM_NO");
			imap.put("DATE_OF_POSTING", "DATE_OF_POSTING");
			imap.put("COUNTRY_OF_ORIGIN", "COUNTRY_OF_ORIGIN");
			imap.put("TERMS_OF_PAYMENT", "TERMS_OF_PAYMENT");
			imap.put("REMARKS", "REMARKS \n test Case \n remark ");

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

			// for mail
			// imap.put("ADDR_TO", "chunghkim@kissco.co.jp");
			// imap.put("ADDR_CC", "chunghkim@kissco.co.jp");
			// imap.put("ADDR_BCC", "chunghkim@kissco.co.jp");
			// imap.put("ADDR_FROM", "chunghkim@kissco.co.jp");
			// imap.put("BARCODE_usps4cb", "12345678901234567890");

			lstMap.add(imap);

		}

		return lstMap;

	}

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData_sagawa(int maxCnt) {

		ArrayList<LinkedHashMap<String, String>> lstMap = new ArrayList<>();

		for (int idx = 0; idx < maxCnt; idx++) {
			LinkedHashMap<String, String> imap = new LinkedHashMap<>();

			// tmpl_sagawa
			imap.put("POST1", "206");
			imap.put("POST2", "9999");
			imap.put("TO", "東京都新宿区西早稲田東ウィング");
			imap.put("QUAN", "999");
			imap.put("PRODUCTNAME", "ＥＩＳＡＮＥＲＰシステム");
			imap.put("OPTION1", "Ｖ");
			imap.put("TEL1", "080-1234-5678");
			imap.put("TEL2", "080-1234-5678");
			imap.put("POST3", "999");
			imap.put("POST4", "9876");
			imap.put("FROM", "東京都新宿区西早稲田東ウィング");
			imap.put("TEL2", "080-1234-5678");
			imap.put("ARTICLENO", "0800-1234-5678");

			lstMap.add(imap);

		}

		return lstMap;

	}

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData_torihiki(int maxCnt) {

		ArrayList<LinkedHashMap<String, String>> lstMap = new ArrayList<>();

		for (int idx = 0; idx < maxCnt; idx++) {
			LinkedHashMap<String, String> imap = new LinkedHashMap<>();

			// tmpl_sagawa
			imap.put("CMPNAME", "COMPANY");
			imap.put("ARTICLE", "PRODUCT20161115");
			imap.put("DATE", "20161115");
			imap.put("LOCATE", "浅草橋");
			imap.put("CONDITION", "契約済み");
			imap.put("DATE1", "20161116");
			imap.put("SHIPNO", "SHIPNO12345");
			imap.put("USERNM", "お名前");
			imap.put("USERMAIL", "SYSTEM@kissco.co.jp");
			imap.put("PRODUCTNAME", "PRODUCTNAME");
			imap.put("PRICE", "1000");
			imap.put("QUANTITY", "99");
			imap.put("AMOUNT", "99000");

			lstMap.add(imap);

		}

		return lstMap;

	}

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData_329N(int maxCnt) {

		ArrayList<LinkedHashMap<String, String>> lstMap = new ArrayList<>();

		for (int idx = 0; idx < maxCnt; idx++) {
			LinkedHashMap<String, String> imap = new LinkedHashMap<>();

			// tmpl_sagawa
			imap.put("YEAR", "2016");
			imap.put("MONTH", "12");
			imap.put("DAY", "12");
			imap.put("TO", "宛先");

			imap.put("AMOUNT", "99999999");
			imap.put("MONEY1", "9999999");
			imap.put("QUAN", "9999999");

			imap.put("HURIKOMISAKI", "'〒１２３-１４１２　東京都 新宿区 西早稲田 東ウィング");

			lstMap.add(imap);

		}

		return lstMap;

	}

	public static ArrayList<LinkedHashMap<String, String>> mkDummyData(int maxCnt) {

		ArrayList<LinkedHashMap<String, String>> lstMap = new ArrayList<>();

		for (int idx = 0; idx < maxCnt; idx++) {
			LinkedHashMap<String, String> imap = new LinkedHashMap<>();

			imap.put("LIST", "partNo" + "_" + String.valueOf(idx));
			imap.put("BARCODE", "123456789012");
			imap.put("IMAGE", "C:\\Users\\KISSCO\\Pictures\\git.png");

			imap.put("STRING", "STRING");
			imap.put("STRING_SP", "STRING");
			imap.put("STRING_SP2", "STRING");
			imap.put("NUMERIC", "0123456789");


			lstMap.add(imap);
		}

		return lstMap;

	}
}
