package cn.cnyirui.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.POJONode;

public class POIUtils {
	public static float ROW_HEIGHT = 30;

	public static void exportToExcel(String dataFields, String fieldTitles, JsonNode jsonNode,
	        OutputStream outputStream) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultRowHeightInPoints(ROW_HEIGHT);
		sheet.createFreezePane(0, 1);

		// 表头
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中格式
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		HSSFFont font = wb.createFont();
		font.setBold(true);
		headerStyle.setFont(font);

		int rowIndex = 0;
		HSSFRow row = sheet.createRow(rowIndex);
		rowIndex++;

		String[] fieldTitleArray = StringUtils.split(fieldTitles, ",");
		for (int i = 0; i < fieldTitleArray.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(fieldTitleArray[i]);
		}
		// 数据
		HSSFCellStyle dataStyle = wb.createCellStyle();
		dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		dataStyle.setWrapText(true);

		String[] dataFieldArray = StringUtils.split(dataFields, ",");
		JsonNode rows = jsonNode.get("rows");
		if (rows != null) {
			rowIndex = exportData(rowIndex, rows, dataFieldArray, sheet, dataStyle);
		}

		rows = jsonNode.get("footer");
		if (rows != null) {
			rowIndex = exportData(rowIndex, rows, dataFieldArray, sheet, dataStyle);
		}
		// 调整列宽
		for (int i = 0; i < fieldTitleArray.length; i++) {
			sheet.autoSizeColumn(i, true);
			int width = sheet.getColumnWidth(i);
			sheet.setColumnWidth(i, width + 1000);
		}
		try {
			wb.write(outputStream);
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static Integer exportData(int rowIndex, JsonNode jsonNode, String[] columns, Sheet sheet,
	        CellStyle dataStyle) {
		if (jsonNode instanceof ArrayNode) {
			for (int i = 0; i < jsonNode.size(); i++) {
				JsonNode jsonNode1 = jsonNode.get(i);
				Row row = sheet.createRow(i + rowIndex);
				for (int j = 0; j < columns.length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellStyle(dataStyle);
					cell.setCellValue(StringUtils.removeEnd(
					        StringUtils.removeStart(jsonNode1.get(columns[j]).toString(), "\""),
					        "\""));
				}
			}
			return rowIndex + jsonNode.size();
		} else if (jsonNode instanceof POJONode) {
			Object pojo = ((POJONode) jsonNode).getPojo();
			if (List.class.isAssignableFrom(pojo.getClass())) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) pojo;
				for (int i = 0; i < list.size(); i++) {
					Row row = sheet.createRow(i + rowIndex);
					for (int j = 0; j < columns.length; j++) {
						Cell cell = row.createCell(j);
						cell.setCellStyle(dataStyle);
						String value = String.valueOf(list.get(i).get(columns[j]));
						cell.setCellValue("null".equals(value) ? "" : value);
					}
				}
				return rowIndex + list.size();
			}
		}
		return rowIndex;
	}

	public static void closeWorkBook(Workbook workbook) {
		if (workbook != null) {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param createHSSF true 创建xls格式，false创建xlsx格式
	 * @param createSheet true同时创建一个sheet
	 * @return
	 */
	public static Workbook createWorkBook(boolean createHSSF, boolean createSheet) {
		Workbook workbook = null;
		if (createHSSF) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
		if (createSheet) {
			Sheet sheet = workbook.createSheet("Sheet1");
			sheet.setDefaultRowHeightInPoints(ROW_HEIGHT);

		}
		return workbook;
	}

	public static Workbook createWorkBook(String fileName) {
		return createWorkBook(new File(fileName));
	}

	public static Workbook createWorkBook(File file) {
		Workbook workbook = null;
		if (file != null) {
			String fileName = file.getName();
			String fileExt = StringUtils.substringAfterLast(fileName, ".");
			try {
				if ("xls".equalsIgnoreCase(fileExt)) {
					workbook = new HSSFWorkbook(new FileInputStream(file));
				} else if ("xlsx".equalsIgnoreCase(fileExt)) {
					workbook = new XSSFWorkbook(file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}

	/**
	 * 取单元格的值
	 * 
	 * @param row
	 * @param cellNum
	 * @return
	 */
	public static Object getCellValue(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return cell.getNumericCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return cell.getBooleanCellValue();
			} else {
				return cell.getStringCellValue();
			}
		}
		return null;
	}

	public static Comment createCellComment(Sheet sheet, String commentValue) {
		Workbook workbook = sheet.getWorkbook();
		Drawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor clientAnchor = null;
		RichTextString richTextString = null;
		if (workbook instanceof HSSFWorkbook) {
			clientAnchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6);
			richTextString = new HSSFRichTextString(commentValue);
		} else if (workbook instanceof XSSFWorkbook) {
			clientAnchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6);
			richTextString = new XSSFRichTextString(commentValue);
		}
		Comment comment = drawing.createCellComment(clientAnchor);
		comment.setString(richTextString);

		return comment;
	}

	/**
	 * 标题行默认样式
	 * 
	 * @param workbook
	 * @return
	 */
	public static CellStyle defaultHeaderRowCellStyle(Workbook workbook) {
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中格式
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		Font font = workbook.createFont();
		font.setBold(true);
		headerCellStyle.setFont(font);
		return headerCellStyle;
	}
}
