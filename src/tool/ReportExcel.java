package tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

public class ReportExcel {

	public static final String REPLACE_TYPE_STRING = "STRING";
	public static final String REPLACE_TYPE_IMG = "IMAGE";
	public static final String REPLACE_TYPE_BARCODE = "BARCODE";
	public static final String REPLACE_KEY = "@@";
	public static final String REPLACE_TYPE_NUMERIC = "NUMERIC";
	public static final String REPLACE_EMPTY = "";


	public static String exportExcel(ReportData rd) throws Exception {

		FileInputStream in = new FileInputStream(rd.getTempletePath());
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFFormulaEvaluator fe = new HSSFFormulaEvaluator(wb);
		HSSFSheet sh;

		int maxPage = 1;
		boolean seperatePage = false;
		boolean doInsertRow = false;
		int maxLst = getMaxList(rd);

		if (rd.maxLstPerPage != 0) {

			// check Max List
			maxPage = maxLst / rd.maxLstPerPage + 1;
			seperatePage = true;

			// sheet Copy for List
			for (int pageIdx = 0; pageIdx < maxPage; pageIdx++) {
				if (pageIdx > 0) {
					wb.cloneSheet(0);
				}
			}
		}

		// TODO catch list Row and insert Row
		for (int pageIdx = 0; pageIdx < maxPage; pageIdx++) {
			sh = wb.getSheetAt(pageIdx);

			for (int idxRow = sh.getFirstRowNum(); idxRow <= sh.getLastRowNum(); idxRow++) {
				if (null == sh.getRow(idxRow)) {
					continue;
				}

				HSSFRow row = sh.getRow(idxRow);

				for (int idxCell = row.getFirstCellNum(); idxCell <= row.getLastCellNum(); idxCell++) {
					if (null == row.getCell(idxCell)) {
						continue;
					}

					// do replace
					HSSFCell cell = row.getCell(idxCell);
					if (cell.getCellType() != Cell.CELL_TYPE_STRING)
						continue;

					String value = cell.getStringCellValue();

					if (value.startsWith(REPLACE_KEY)) {

						ReportItem item = rd.getItem(value.replaceAll(REPLACE_KEY, ""));

						if (null == item) {
							cell.setCellValue(REPLACE_EMPTY);
							continue;
						}

						// get list index for Page
						int maxCnt = 1;
						if (item.isLst) {

							if (seperatePage) {
								maxCnt = rd.maxLstPerPage;
								if (pageIdx + 1 == maxPage) {
									maxCnt = maxLst % rd.maxLstPerPage;
								}
								// Case insert Row
							} else {
								maxCnt = maxLst;
								// TODO insertRow
								if (!doInsertRow) {
									for (int i = 1; i < maxLst; i++) {
										copyRow(wb, sh, cell.getRowIndex(), cell.getRowIndex() + 1);
									}

									doInsertRow = true;
								}
							}

						}

						for (int i = 0; i < maxCnt; i++) {

							String val;

							if (item.isLst) {
								if (seperatePage) {
									val = item.lstValue.get((pageIdx * rd.maxLstPerPage) + i);
								} else {
									val = item.lstValue.get(i);
								}
							} else {
								val = item.lstValue.get(0);
							}

							// get Cell
							HSSFCell targetCell = sh.getRow(idxRow + i).getCell(idxCell);

							replaceData(item, val, wb, sh, targetCell);

						}
					}
				}
			}

		}

		//refresh
		HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);

		String exportPath = rd.getExportFolder() + rd.exportFileNM + commonUtil.getStringDatetimeForFile(new Date())
				+ ".xls";

		if (rd.reportType == ReportProc.REPORT_TYPE_PDF) {
			exportPath = commonUtil.getTempFolder() + rd.exportFileNM + commonUtil.getStringDatetimeForFile(new Date())
					+ ".xls";
		}

		// check File exist
		if (ReportFile.existFile(exportPath)) {
			System.out.println("ERROR: File exist_" + exportPath);
			throw new IOException();
		}

		FileOutputStream out = new FileOutputStream(exportPath);
		wb.write(out);
		out.close();

		if (rd.reportType != ReportProc.REPORT_TYPE_PDF) {
			System.out.println("出力完了：" + exportPath);
		}

		return exportPath;

	}

	/***
	 * データ入れ替え処理
	 *
	 * @param item
	 * @param val
	 * @param wb
	 * @param sh
	 * @param targetCell
	 * @throws Exception
	 */
	private static void replaceData(ReportItem item, String val, HSSFWorkbook wb, HSSFSheet sh, HSSFCell targetCell)
			throws Exception {

		switch (item.type.toUpperCase()) {
		case REPLACE_TYPE_STRING:

			targetCell.setCellValue(val);

			break;
		case REPLACE_TYPE_NUMERIC:
			CellStyle cs = targetCell.getCellStyle();
			targetCell.setCellValue("0");
			cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
			targetCell.setCellStyle(cs);
			targetCell.setCellValue(Double.parseDouble(val));
			break;
		case REPLACE_TYPE_IMG:

			replaceImg(val, wb, sh, targetCell);

			break;
		case REPLACE_TYPE_BARCODE:

			String imgPath = barCode.mkBarcode(val, item.option1);

			replaceImg(imgPath, wb, sh, targetCell);

			// temp file delete
			commonUtil.deleteFile(imgPath);

			break;
		default:
			System.out.println("★★★★確認★★★★");
			System.out.println(item.type + " is Undefined replace Type");
			System.out.println("★★★★★★★★★★");
			break;
		}

	}

	private static int getMaxList(ReportData rd) {
		int maxlst = 0;

		for (Entry<String, ReportItem> elem : rd.items.entrySet()) {
			if (maxlst < elem.getValue().lstValue.size()) {
				maxlst = elem.getValue().lstValue.size();
			}
		}
		return maxlst;
	}

	/**
	 * イメージ切り替え
	 *
	 * @param path
	 * @param wb
	 * @param sh
	 * @param tagetCell
	 * @throws Exception
	 */
	private static void replaceImg(String path, HSSFWorkbook wb, HSSFSheet sh, HSSFCell targetCell) throws Exception {
		InputStream inImg = new FileInputStream(path);

		byte[] byteImg = IOUtils.toByteArray(inImg);

		// TODO MIME
		int pictureIndex = wb.addPicture(byteImg, Workbook.PICTURE_TYPE_JPEG);
		inImg.close();

		CreationHelper helper = wb.getCreationHelper();
		Drawing drawingPatriarch = sh.createDrawingPatriarch();
		ClientAnchor anchor = helper.createClientAnchor();

		anchor.setCol1(targetCell.getColumnIndex());
		anchor.setRow1(targetCell.getRowIndex());

		Picture pict = drawingPatriarch.createPicture(anchor, pictureIndex);
		pict.resize();

	}

	@SuppressWarnings("deprecation")
	private static void copyRow(HSSFWorkbook workbook, HSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
		// Get the source / new row
		HSSFRow newRow = worksheet.getRow(destinationRowNum);
		HSSFRow sourceRow = worksheet.getRow(sourceRowNum);

		// If the row exist in destination, push down all rows by 1 else create
		// a
		// new row

		if (newRow != null) {
			worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1, true, true);

		} else {
			newRow = worksheet.createRow(destinationRowNum);
		}

		// Loop through source columns to add to new row
		for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
			// Grab a copy of the old/new cell
			HSSFCell oldCell = sourceRow.getCell(i);
			HSSFCell newCell = newRow.createCell(i);

			// If the old cell is null jump to next cell
			if (oldCell == null) {
				newCell = null;
				continue;
			}

			// TODO style overflow error
			// Copy style from old cell and apply to new cell
			HSSFCellStyle newCellStyle = workbook.createCellStyle();
			newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
			;
			newCell.setCellStyle(newCellStyle);

			// // If there is a cell comment, copy
			// if (newCell.getCellComment() != null) {
			// newCell.setCellComment(oldCell.getCellComment());
			// }
			//
			// // If there is a cell hyperlink, copy
			// if (oldCell.getHyperlink() != null) {
			// newCell.setHyperlink(oldCell.getHyperlink());
			// }
			//
			// // Set the cell data type
			// newCell.setCellType(oldCell.getCellType());
			//
			// // Set the cell data value
			// switch (oldCell.getCellType()) {
			// case Cell.CELL_TYPE_BLANK :
			// newCell.setCellValue(oldCell.getStringCellValue());
			// break;
			// case Cell.CELL_TYPE_BOOLEAN :
			// newCell.setCellValue(oldCell.getBooleanCellValue());
			// break;
			// case Cell.CELL_TYPE_ERROR :
			// newCell.setCellErrorValue(oldCell.getErrorCellValue());
			// break;
			// case Cell.CELL_TYPE_FORMULA :
			// newCell.setCellFormula(oldCell.getCellFormula());
			// break;
			// case Cell.CELL_TYPE_NUMERIC :
			// newCell.setCellValue(oldCell.getNumericCellValue());
			// break;
			// case Cell.CELL_TYPE_STRING :
			// newCell.setCellValue(oldCell.getRichStringCellValue());
			// break;
			// }

		}

		// If there are are any merged regions in the source row, copy to new
		// row
		for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
			CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
			if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
						(newRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
						cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
				worksheet.addMergedRegion(newCellRangeAddress);
			}
		}
	}
}
