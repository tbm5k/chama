package com.chama.app.services;

import com.chama.app.models.UserIntegrations;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    UserIntegrationsService userIntegrationsService;

    public void exportReport(int chamaId, String reportName) throws JRException, FileNotFoundException {

        List<UserIntegrations> members = userIntegrationsService.getChamaMembers(chamaId);

        File file = null;

        switch (reportName){
            case "members":
                file = ResourceUtils.getFile("classpath:reports/userIntegrations.jrxml");
                break;
            case "loans":
                file = ResourceUtils.getFile("classpath:reports/loans.jrxml");
                break;
            case "contributions":
                file = ResourceUtils.getFile("classpath:reports/contributions.jrxml");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(members);
        Map<String, Object> map = new HashMap<>();
        map.put("createdBy", "Chama");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\tbm5k\\Downloads\\" + createFileName(reportName));
    }

    public String createFileName(String name){
        return name.concat(".pdf");
    }

}
