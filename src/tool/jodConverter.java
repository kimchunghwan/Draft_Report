package tool;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.junit.AfterClass;

public class jodConverter {

	static OfficeManager officeManager;
	static OfficeDocumentConverter converter;

	/**
	 * based on LibreOffice
	 */
	public jodConverter() {
		super();
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

		String osName = System.getProperty("os.name");

		// TODO 오피스 폴더 경로 설정하는 부분을 config 파일에 정의 하기
		if (osName.toLowerCase().contains("windows")) {
			//config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");
			config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");
		} else if (osName.toLowerCase().contains("linux")) {
			config.setOfficeHome("/usr/lib/libreoffice");
		} else {
			System.out.println("ERROR : not fined match OS");
		}
		config.setPortNumber(8100);


		officeManager = config.buildOfficeManager();
		officeManager.start();

		converter = new OfficeDocumentConverter(officeManager);

	}

	/**
	 *
	 *
	 * @param excelPath
	 * @param exportPath
	 * @param exportFolderPath
	 */
	public void convertFile(String excelPath, String exportPath, String exportFolderPath) {

//		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
//
//		String osName = System.getProperty("os.name");
//
//		// TODO 오피스 폴더 경로 설정하는 부분을 config 파일에 정의 하기
//		if (osName.toLowerCase().contains("windows")) {
//			config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");
//		} else if (osName.toLowerCase().contains("linux")) {
//			config.setOfficeHome("/usr/lib/libreoffice");
//		} else {
//			System.out.println("ERROR : not fined match OS");
//		}
//		config.setPortNumber(8100);
//
//		officeManager = config.buildOfficeManager();
//		officeManager.start();
//
//		converter = new OfficeDocumentConverter(officeManager);


		File tempExcelFile = new File(excelPath);

		ReportFile.checkAndMakeDirectory(exportFolderPath);

		File exportFile = new File(exportPath);

		converter.convert(tempExcelFile, exportFile);
		System.out.println("出力完了：" + exportPath);

	}

	@AfterClass
	public void stop() {
		officeManager.stop();
	}

}
