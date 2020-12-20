package com.chama.app.controllers;

import com.chama.app.models.Receipt;
import com.chama.app.models.UserIntegrations;
import com.chama.app.services.ReceiptService;
import com.chama.app.services.SequenceService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        List<UserIntegrations> list = userIntegrationsService.getChamaMembers(1);
        model.addAttribute("members", userIntegrationsService.getChamaMembers(1));
        model.addAttribute("receipt", new Receipt());
        return "fragments/receipt/receipt";
    }

    @PostMapping("/receipt")
    public String setReceipt(Receipt receipt){

        Calendar calendar = Calendar.getInstance();

        if(!receipt.getContributionType().equals("Contributions"))
            receipt.setMemberId(null);

        //setting the receipt number
        int num = receiptService.findTotal();
        String prefix = sequenceService.findChamaSequence(1);//dynamically set the chama id
        String receiptNumber = prefix + "/" + num + "/" + calendar.get(Calendar.YEAR);
        receipt.setReceiptNumber(receiptNumber);//saving the receipt number

        receiptService.addNewReceipt(receipt);

        if(receipt.getPaymentDescription().equals("Loan")){
            return "redirect:allocation";
        }else {
            return "redirect:receipt";
        }
    }

}
