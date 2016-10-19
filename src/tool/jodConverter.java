package tool;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class jodConverter {


	public static void convertFile(String excelPath, String exportPath) {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

		// とりあえずサンプルなのでベタに設定
		config.setOfficeHome("C:\\Program Files (x86)\\LibreOffice 5");

		config.setPortNumber(8100);

		// //本来はタイムアウトの設定とかもしたほうが良い

		OfficeManager officeManager = config.buildOfficeManager();
		officeManager.start();

		OfficeDocumentConverter converter = new OfficeDocumentConverter(
				officeManager);

		File tempExcelFile = new File(excelPath);
		File exportFile = new File(exportPath);

		converter.convert(tempExcelFile, exportFile);
		System.out.println("出力完了：" + exportPath);

		officeManager.stop();

	}

	//TODO check environment linux


}
