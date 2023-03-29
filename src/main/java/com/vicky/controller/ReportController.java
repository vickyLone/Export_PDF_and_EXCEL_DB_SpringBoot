package com.vicky.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;
import com.vicky.entity.Reports;
import com.vicky.helper.ExcelCreator;
import com.vicky.helper.PdfCreater;
import com.vicky.service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	
	@Autowired
    private ReportsService reportService;

    @GetMapping("/export-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
    	
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=Reports" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Reports> listOfReports = reportService.getListOfReports();
        ExcelCreator generator = new ExcelCreator(listOfReports);
        generator.generateExcelFile(response);
    }
    
    @GetMapping("/export-pdf")
	public void exportToPdfFile(HttpServletResponse response) throws IOException, DocumentException {
		
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Reports" +currentDateTime+ ".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<Reports> listOfReports = reportService.getListOfReports();
		PdfCreater create = new PdfCreater();
		create.create(listOfReports, response);
		
		
	}

}
