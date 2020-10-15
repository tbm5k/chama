package com.chama.app.controllers.multipledata;

import com.chama.app.services.multipledata.ExcelReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ExcelReceiptController {

    @Autowired
    ExcelReceiptService receiptService;

    @GetMapping("/uploadReceipt")
    public String getFileUploadPage(){
        return "fragments/receipt/receipt-upload";
    }

    @PostMapping("/uploadReceipt")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model){
        receiptService.addReceipts(multipartFile);
        model.addAttribute("receipts", receiptService.getChamaReceipts(1));//dynamically set the chama id
        //receiptService.validateRecords(1);//fetch chama id from session
        return "fragments/receipt/receipt-upload";
    }


    //validation test method
    @GetMapping("/v")
    public String validate(Model model){
        //receiptService.validateRecords(1);//fetch chama id from session
        model.addAttribute("receipts", receiptService.getChamaReceipts(1));//dynamically set the chama id
        return "redirect:uploadReceipt";
    }

}
