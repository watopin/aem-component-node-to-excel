package com.sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private final String filePath;
	private final String fileName;
	private Workbook book;
	
	public Excel(String path, String name) {
		this.filePath = path;
		this.fileName = name;
		this.book = new XSSFWorkbook();
	}
	
	public void simpleWrite(List<Map<Integer, String>> tableData) {
		Sheet sheet = book.createSheet("sheetname");
		sheet.setDefaultColumnWidth(3);
		int rownum = 0;
		for (Map<Integer, String> rowdata : tableData) {
			Row r = sheet.createRow(rownum);
			rownum++;
			for (Integer i : rowdata.keySet()) {
				r.createCell(i).setCellValue(rowdata.get(i));
			}
		}
		this.save();
	}
	
	public void testwrite(List<NodeTableRow> tabledata) {
		// sheet settings
		Sheet sheet = book.createSheet("nodetable");
		sheet.setDefaultColumnWidth(3);
		sheet.setDefaultRowHeight((short)336);
		
		int rownum = 0;
		for (NodeTableRow rowdata : tabledata) {
			Row r = sheet.createRow(rownum);
			for(int i =0 ; i<rowdata.getLastCellNo(); i++) {
				r.createCell(i);
			}
			rownum++;
			this.writeRow(r, rowdata.stringValues); // cell string value
			
			// set style(border and color)
			r.getCell(rowdata.namePosition).setCellStyle(this.styleForNodeNameCell());
			
			for(int i = rowdata.namePosition -1 ; i > 3; i--) {
				r.getCell(i).setCellStyle(this.styleForBeforeNodeName());
			}
			
			for (int i = rowdata.namePosition + 1; i < rowdata.primaryTypePosition; i++) {
				r.getCell(i).setCellStyle(this.styleNameCellToType());
			}
			r.getCell(rowdata.primaryTypePosition).setCellStyle(this.styleBorderExceptRight());
			for(int i = rowdata.primaryTypePosition +1; i < rowdata.getResourceTypePosition(); i++) {
				r.getCell(i).setCellStyle(this.styleBorderTopBottom());
			}
			r.getCell(rowdata.getResourceTypePosition()).setCellStyle(this.styleBorderExceptRight());
			for(int i = rowdata.getResourceTypePosition() + 1; i < rowdata.getLastCellNo() - 1; i++) {
				r.getCell(i).setCellStyle(this.styleBorderTopBottom());
			}
			r.getCell(rowdata.getLastCellNo()-1).setCellStyle(this.styleBorderExceptLeft());
		}
		this.save();
	}
	
	private void writeRow(Row row, Map<Integer, String> vals) {
		for (Integer i : vals.keySet()) {
			row.getCell(i).setCellValue(vals.get(i));
		}
	}
	
	public void save() {
		try (OutputStream fileout = new FileOutputStream(filePath + fileName)){
			this.book.write(fileout);
		} catch (IOException e) {
			throw new RuntimeException("cannot save", e);
		}
	}
	
	private CellStyle styleBorderTopBottom() {
		CellStyle style = book.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}
	
	private CellStyle styleForNodeNameCell() {
		CellStyle style = book.createCellStyle();
		style.setBorderLeft(BorderStyle.THIN);		
		style.setBorderTop(BorderStyle.THIN);
		// foreground color doesn't mean font color
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
	
	private CellStyle styleNameCellToType() {
		CellStyle style = book.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
	
	private CellStyle styleBorderExceptRight() {
		CellStyle style = book.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}
	
	private CellStyle styleBorderExceptLeft() {
		CellStyle style = book.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}
	
	private CellStyle styleForBeforeNodeName() {
		CellStyle style = book.createCellStyle();
		style.setBorderLeft(BorderStyle.THIN);
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
}
