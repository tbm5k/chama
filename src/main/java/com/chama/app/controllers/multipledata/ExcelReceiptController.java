package com.chama.app.controllers.multipledata;

import com.chama.app.models.Receipt;
import com.chama.app.models.multipledata.ExcelReceipt;
import com.chama.app.services.ReceiptService;
import com.chama.app.services.multipledata.ExcelReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class ExcelReceiptController {

    @Autowired
    ExcelReceiptService excelReceiptService;
    @Autowired
    ReceiptService receiptService;

    List<ExcelReceipt> receiptList;
    final int chamaId = 1; //fetch the chamaId from the session

    @GetMapping("/uploadReceipt")
    public String getFileUploadPage(){
        return "fragments/receipt/receipt-upload";
    }

    @PostMapping("/uploadReceipt")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile){
        excelReceiptService.addReceipts(multipartFile);
        //receiptService.validateRecords(1);//fetch chama id from session
        return "redirect:receiptPreview";
    }

    //validation test method
    @GetMapping("/receiptPreview")
    public String validate(Model model){
        //receiptService.validateRecords(1);//fetch chama id from session
        receiptList = excelReceiptService.getChamaReceipts(chamaId);
        model.addAttribute("receipts", receiptList);
        return "fragments/receipt/receipts-preview";
    }

    @GetMapping("/isReceiptsValid")
    public String isValid(@RequestParam String valid){
        if(valid.equals("Approve")){
            for(ExcelReceipt excelReceipt: receiptList){
                Receipt receipt = new Receipt();
                receipt.setMemberId(excelReceipt.getUserId());
                receipt.setReceiptNumber(excelReceipt.getReceiptNumber());
                receipt.setReceiptAmount(excelReceipt.getReceiptAmount());
                receipt.setReceiptDate(excelReceipt.getReceiptDate());
                receipt.setPaymentMode(excelReceipt.getPaymentMode());
                receipt.setPaymentDescription(excelReceipt.getPaymentDescription());
                receipt.setReceiptStatus(excelReceipt.getReceiptStatus());
                receipt.setContributionType(excelReceipt.getContributionType());
                receipt.setReceiptType(excelReceipt.getReceiptType());

                //add receipt to the receipt records
                receiptService.addNewReceipt(receipt);
            }

            //deleting records of the specified chama to relieve the memory
            excelReceiptService.clearRecords(chamaId);
            return "redirect:chamaDashboard";
        }else {
            //clear the incorrect records for re-uploading
            excelReceiptService.clearRecords(chamaId);
            return "fragments/receipt/receipt-upload";
        }
    }

    @PostMapping("/editField")
    public String editField(@ModelAttribute("excelReceipt")ExcelReceipt excelReceipt, Map<String, ExcelReceipt> model){
        model.put("excelReceipt", excelReceipt);
        return "fragments/receipt/update-receipt";
    }

    @PostMapping("/updateExcelReceipt")
    public String excelReceiptUpdate(@ModelAttribute("receipt")ExcelReceipt excelReceipt){
        excelReceiptService.updateRecord(excelReceipt);
        return "redirect:receiptPreview";
    }

}
