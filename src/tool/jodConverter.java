package tool;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class jodConverter {
W
	/**
	 * version Windows OS
	 * 
	 * @param excelPath
	 * @param exportPath
	 */
	public static void convertFile(String excelPath, String exportPath) {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

		// とりあえずサンプルなのでベタに設定
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		// Windows OS
		// config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");
		// Windows linux
		if(osName.toLowerCase().contains("windows")){
			config.setOfficeHome("/usr/lib/libreoffice");
		}else if(osName.toLowerCase().contains("linux")){
			config.setPortNumber(8100);
		}else {
			System.out.println("ERROR : not fined match OS");
		}
		

		

		// //本来はタイムアウトの設定とかもしたほうが良い

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

	// TODO check environment linux
	public static void convertFileL(String excelPath, String exportPath) {

	}

}
