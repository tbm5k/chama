package com.chama.app.controllers;

import com.chama.app.models.Receipt;
import com.chama.app.services.ReceiptService;
import com.chama.app.services.SequenceService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;

@Controller
public class ReceiptController {

    @Autowired
    UserIntegrationsService userIntegrationsService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    SequenceService sequenceService;

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        //dynamically set the chama id
        model.addAttribute("members", userIntegrationsService.getChamaMembers(1));
        model.addAttribute("receipt", new Receipt());
        return "fragments/receipt/receipt";
    }

    @PostMapping("/receipt")
    public String setReceipt(Receipt receipt){

        Calendar calendar = Calendar.getInstance();
        Receipt receiptHolder = new Receipt();

        receiptHolder.setReceiptId(receipt.getReceiptId());
        receiptHolder.setUuid(receipt.getUuid());

        if(receipt.getContributionType().equals("Contributions"))
            receiptHolder.setMemberId(receipt.getMemberId());
/*
        else
            receiptHolder.setMemberId(null);
 */
        receiptHolder.setContributionType(receipt.getContributionType());


        //setting the receipt number
        int num = receiptService.findTotal();
        String prefix = sequenceService.findChamaSequence(1);//dynamically set the chama id
        String receiptNumber = prefix + "/" + num + "/" + calendar.get(Calendar.YEAR);

        receiptHolder.setReceiptNumber(receiptNumber);//saving the receipt number
        receiptHolder.setReceiptAmount(receipt.getReceiptAmount());
        receiptHolder.setReceiptDate(receipt.getReceiptDate());
        receiptHolder.setPaymentMode(receipt.getPaymentMode());
        receiptHolder.setPaymentDescription(receipt.getPaymentDescription());
        receiptHolder.setReceiptType(receipt.getReceiptType());

        receiptService.addNewReceipt(receiptHolder);
        return "redirect:allocation";
    }
    
}
