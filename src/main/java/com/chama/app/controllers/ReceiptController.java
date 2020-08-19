package com.chama.app.controllers;

import com.chama.app.models.Receipt;
import com.chama.app.services.ReceiptService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReceiptController {

    @Autowired
    UserIntegrationsService userIntegrationsService;
    @Autowired
    ReceiptService receiptService;

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        //dynamically set the chama id
        model.addAttribute("members", userIntegrationsService.findByChamaForeignKey(2));
        model.addAttribute("receipt", new Receipt());
        return "fragments/receipt/receipt";
    }

    @PostMapping("/receipt")
    public String setReceipt(Receipt receipt){

        Receipt receiptHolder = new Receipt();
        receiptHolder.setId(receipt.getId());
        receiptHolder.setUuid(receipt.getUuid());
        receiptHolder.setMemberId(receipt.getMemberId());
        receiptHolder.setReceiptNumber(receipt.getReceiptNumber());//call the sequence entity to set the receipt number
        receiptHolder.setReceiptAmount(receipt.getReceiptAmount());
        receiptHolder.setReceiptDate(receipt.getReceiptDate());
        receiptHolder.setPaymentMode(receipt.getPaymentMode());
        receiptHolder.setPaymentDescription(receipt.getPaymentDescription());
        receiptHolder.setReceiptType(receipt.getReceiptType());

        receiptService.addNewReceipt(receiptHolder);
        return "fragments/receipt/receipt";
    }
    
}
