package com.chama.app.controllers.multipledata;

import com.chama.app.models.Allocation;
import com.chama.app.models.multipledata.ExcelAllocation;
import com.chama.app.services.multipledata.ExcelAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/allocationsPreview")
    public String validate(Model model){
        List<ExcelAllocation> allocationList = allocationService.getChamaAllocations(1);//dynamically set the chama id
        model.addAttribute("allocations", allocationList);
        return "fragments/allocation/allocations-preview";
    }
}
