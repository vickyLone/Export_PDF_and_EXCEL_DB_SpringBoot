package com.vicky.helper;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vicky.entity.Reports;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelCreator {
	
	 private List < Reports > reportList;
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;

	    public ExcelCreator(List < Reports > reportList) {
	        this.reportList = reportList;
	        workbook = new XSSFWorkbook();
	    }
	    private void writeHeader() {
	        sheet = workbook.createSheet("Student");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "Name", style);
	        createCell(row, 2, "Gender", style);
	    }
	    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (valueOfCell instanceof Integer) {
	            cell.setCellValue((Integer) valueOfCell);
	        } else if (valueOfCell instanceof Long) {
	            cell.setCellValue((Long) valueOfCell);
	        } else if (valueOfCell instanceof String) {
	            cell.setCellValue((String) valueOfCell);
	        } else {
	            cell.setCellValue((Boolean) valueOfCell);
	        }
	        cell.setCellStyle(style);
	    }
	    private void write() {
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        for (Reports record: reportList) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell(row, columnCount++, record.getUid(), style);
	            createCell(row, columnCount++, record.getName(), style);
	            createCell(row, columnCount++, record.getGender(), style);
	            createCell(row, columnCount++, record.getEmail(), style);

	        }
	    }
	    public void generateExcelFile(HttpServletResponse response) throws IOException {
	        writeHeader();
	        write();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }

}
