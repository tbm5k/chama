package com.chama.app.controllers.multipledata;

import com.chama.app.models.Allocation;
import com.chama.app.models.multipledata.ExcelAllocation;
import com.chama.app.services.AllocationService;
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
    ExcelAllocationService excelAllocationService;
    @Autowired
    AllocationService allocationService;

    List<ExcelAllocation> allocationList;
    final int chamaId = 1;//fetch the chama ID from the session

    @GetMapping("/uploadAllocations")
    public String getFileUploadPage(){
        return "fragments/allocation/allocation-upload";
    }

    @PostMapping("/uploadAllocations")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile){
        try {
            excelAllocationService.addAllocations(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:allocationsPreview";
    }

    @GetMapping("/allocationsPreview")
    public String validate(Model model){
        allocationList = excelAllocationService.getChamaAllocations(chamaId);
        model.addAttribute("allocations", allocationList);
        return "fragments/allocation/allocations-preview";
    }

    @GetMapping("/isAllocationsValid")
    public String isValid(@RequestParam String valid){

        if(valid.equals("Approve")){
            for(ExcelAllocation excelAllocation : allocationList){
                Allocation allocation = new Allocation();
                allocation.setAmount(excelAllocation.getAllocationAmount());
                allocation.setReceiptDate(excelAllocation.getAllocationDate());
                allocation.setReceiptNumber(excelAllocation.getReceiptNumber());
                allocation.setAllocationPeriod(excelAllocation.getAllocationPeriod());
                allocation.setMemberId(excelAllocation.getMemberId());

                //add allocation to the DB
                allocationService.addAllocation(allocation);
            }
            excelAllocationService.clearRecords(chamaId);
            return "redirect:chamaDashboard";
        }else {
            //clear records for re-uploading
            excelAllocationService.clearRecords(chamaId);
            return "fragments/allocation/allocation-upload";
        }
    }
}
