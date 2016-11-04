package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReportFile {
	public static final String EXT_SQL = ".sql";

	public static final String EXT_XLSX = ".xlsx";
	public static final String EXT_CSV = ".csv";
	public static final String EXT_TXT = ".txt";
	public static final String EXT_XML = ".xml";
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String CHARSET_SJIS = "SJIS";
	private static final String UTF8_BOM = "\uFEFF";

	public static String readFile(String path, String encoding) throws IOException {
		StringBuilder text = new StringBuilder();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader inFile = null;

		fis = new FileInputStream(path);
		isr = new InputStreamReader(fis, encoding);
		inFile = new BufferedReader(isr);
		String buffer = null;
		boolean firstLine = true;
		while ((buffer = inFile.readLine()) != null) {
			if (firstLine) {
				buffer = removeUTF8BOM(buffer);
				firstLine = false;
			}
			text.append(buffer);
			text.append(commonUtil.STR_NEWLINE);
		}
		if (inFile != null) {
			inFile.close();
		}
		if (isr != null) {
			isr.close();
		}
		if (fis != null) {
			fis.close();
		}
		return commonUtil.toString(text);
	}

	private static String removeUTF8BOM(String firstLine) {
		if (firstLine.startsWith(UTF8_BOM)) {
			firstLine = firstLine.substring(commonUtil.INT_ONE);
		}
		return firstLine;
	}

	public static Boolean existFile(String filePath, String fileName) {
		filePath = filePath + File.separator + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}


	/*
	 * check file exist
	 */
	public static Boolean existFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static String getNextFileName(final String filePath, final String prefix, final String extension) {

		File fileDir = new File(filePath);
		String strDate = ReportDate.getSysDateTime("yyMMdd");
		StringBuilder regex = new StringBuilder();
		regex.append('^');
		regex.append(prefix);
		regex.append(strDate);
		regex.append("-\\d{3}");
		regex.append(extension);
		regex.append('$');
		String[] fileNames = fileDir.list(new WeFilenameFilter(regex.toString()));

		String strNextSeq = "001";
		if (fileNames.length > commonUtil.INT_ZERO) {
			String fileName = fileNames[fileNames.length - 1];
			int lastIndex = fileName.lastIndexOf('-');
			String strSeq = fileName.substring(lastIndex + 1, lastIndex + 4);
			int nextSeq = commonUtil.toInt(strSeq) + 1;
			strNextSeq = String.format("%1$03d", nextSeq);
		}

		StringBuilder nextFileName = new StringBuilder();
		nextFileName.append(strDate);
		nextFileName.append('-');
		nextFileName.append(strNextSeq);

		return nextFileName.toString();

	}

	public static String[] getFileList(final String filePath, final String prefix, final String extension) {
		File fileDir = new File(filePath);
		StringBuilder regex = new StringBuilder();
		regex.append('^');
		regex.append(prefix);
		regex.append(".+");
		regex.append(extension);
		regex.append('$');
		String[] fileNames = fileDir.list(new WeFilenameFilter(regex.toString()));
		return fileNames;
	}

	public static void checkAndMakeDirectory(String dirPath) {
		File dir = new File(dirPath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
	}

}
