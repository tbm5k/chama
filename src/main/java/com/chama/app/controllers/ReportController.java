package com.chama.app.controllers;

import com.chama.app.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;
    private int chamaId = 2;

    @GetMapping("/report/{reportName}")
    public String generateReport(@PathVariable("reportName") String reportName) throws FileNotFoundException, JRException {
        reportService.exportReport(chamaId, reportName);
        return "redirect:/chamaDashboard";
    }

}
