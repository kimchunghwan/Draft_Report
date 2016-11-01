package tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.MimeTypes;

/*
 *
 * Using barcode4j
 * @author KISSCO
 *
 */
public class barCode {

	/**
	 * バーコードイメージ 情報設定
	 *
	 * @param barcodeData
	 * @param barcodeType
	 * @return outputFile 出力位置
	 */
	static String mkBarcode(String barcodeData, String barcodeType) {
		/*
		 * "codabar", "code39", "postnet", "intl2of5", "ean-128" "royal-mail-cbc",
		 * "ean-13", "itf-14", "datamatrix", "code128" "pdf417", "upc-a", "upc-e",
		 * "usps4cb", "ean-8", "ean-13"
		 */

		final int dpi = 203;

		/*
		 * format: SVG, EPS, TIFF, JPEG, PNG, GIF, BMP
		 */
		String fileFormat = "jpg";

		String fileName = "barcodetest_" + barcodeType;
		String outputPath = commonUtil.getTempFolder() + fileName
				+ commonUtil.getStringDatetimeForFile(new Date()) + "." + fileFormat;

		/* anti-aliasing */
		boolean isAntiAliasing = true;

		createBarcode(barcodeType, barcodeData, fileFormat, isAntiAliasing, dpi,
				outputPath);
		return outputPath;
	}

	/**
	 * バーコードイメージ作成
	 *
	 * @param barcodeType
	 * @param barcodeData
	 * @param fileFormat
	 * @param isAntiAliasing
	 * @param dpi
	 * @param outputFile
	 */
	@SuppressWarnings("rawtypes")
	private static void createBarcode(String barcodeType, String barcodeData,
			String fileFormat, boolean isAntiAliasing, int dpi, String outputFile) {
		try {


			AbstractBarcodeBean bean = null;

			BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
			Class clazz = resolver.resolveBean(barcodeType);
			bean = (AbstractBarcodeBean) clazz.newInstance();
			bean.doQuietZone(true);

			// Open output file
			OutputStream out = new FileOutputStream(new File(outputFile));
			try {

				// Set up the canvas provider for monochrome JPEG output
				String mimeType = MimeTypes.expandFormat(fileFormat);
				int imageType = BufferedImage.TYPE_BYTE_BINARY;
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, mimeType,
						dpi, imageType, isAntiAliasing, 0);

				// Generate the barcode
				bean.generateBarcode(canvas, barcodeData);

				// Signal end of generation
				canvas.finish();

				System.out.println("create image success.");
			} finally {
				out.close();
			}
		} catch (Exception e) {
			System.out.println("create image fail.");
			System.out.println("barcodeType : " + barcodeType);
			e.printStackTrace();
		}

	}
}
