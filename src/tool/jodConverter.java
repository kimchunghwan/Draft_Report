package tool;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class jodConverter {

	/**
	 * 오피스 폴더에 대해서는 이후에 따로 최적화가 필요함 .
	 *
	 * @param excelPath
	 * @param exportPath
	 */
	public static void convertFile(String excelPath, String exportPath) {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

		String osName = System.getProperty("os.name");

		// TODO 오피스 폴더 경로 설정하는 부분을 config 파일에 정의 하기
		if (osName.toLowerCase().contains("windows")) {
			config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");
		} else if (osName.toLowerCase().contains("linux")) {
			config.setOfficeHome("/usr/lib/libreoffice");
		} else {
			System.out.println("ERROR : not fined match OS");
		}
		config.setPortNumber(8100);

		OfficeManager officeManager = config.buildOfficeManager();
		officeManager.start();

		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

		File tempExcelFile = new File(excelPath);

		WeFile.checkAndMakeDirectory(exportPath);
		File exportFile = new File(exportPath);

		converter.convert(tempExcelFile, exportFile);
		System.out.println("出力完了：" + exportPath);

		officeManager.stop();

	}

}
