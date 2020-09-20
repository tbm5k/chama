package com.chama.app.controllers.multipledata;

import com.chama.app.services.multipledata.ExcelAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ExcelAllocationController {

    @Autowired
    ExcelAllocationService allocationService;

    @GetMapping("/uploadAllocations")
    public String getFileUploadPage(){
        return "fragments/allocation/allocation-upload";
    }

    @PostMapping("/uploadAllocations")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile){
        try {
            allocationService.addAllocations(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fragments/allocation/allocation-upload";
    }
}
