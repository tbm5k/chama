package com.chama.app.controllers;

import com.chama.app.models.Allocation;
import com.chama.app.models.Loan;
import com.chama.app.models.Receipt;
import com.chama.app.models.UserIntegrations;
import com.chama.app.services.LoanService;
import com.chama.app.services.ReceiptService;
import com.chama.app.services.SequenceService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    @Autowired
    LoanService loanService;

    private int chamaId = 2;//get chama from the session obj

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        List<UserIntegrations> list = userIntegrationsService.getChamaMembers(chamaId);
        model.addAttribute("members", userIntegrationsService.getChamaMembers(chamaId));
        model.addAttribute("receipt", new Receipt());
        return "fragments/receipt/receipt";
    }

    @PostMapping("/receipt")
    public String setReceipt(Receipt receipt, Model model){
        Calendar calendar = Calendar.getInstance();

        receipt.setMember(userIntegrationsService.getMember(receipt.getMemberId()).get());

        /*
        bug during form submission in that the member object does not get sent
         */

        if(!receipt.getContributionType().equals("Contributions"))
            receipt.setMember(null);

        //setting the receipt number
        int num = receiptService.findTotal();
        String prefix = sequenceService.findChamaSequence(chamaId);
        String receiptNumber = prefix + "/" + num + "/" + calendar.get(Calendar.YEAR);
        receipt.setReceiptNumber(receiptNumber);//saving the receipt number

        receiptService.addNewReceipt(receipt);

        if(receipt.getPaymentDescription().equals("Loan")){
            Loan loan = loanService.getQueuedLoan(receipt.getMember().getUserIntegrationsId());

            Allocation allocation = new Allocation();
            allocation.setAmount(receipt.getReceiptAmount());
            allocation.setReceiptDate(receipt.getReceiptDate());
            allocation.setReceiptNumber(receipt.getReceiptNumber());
            allocation.setAllocationPeriod(loan.getLoanPeriod());

            model.addAttribute("allocation", allocation);
            model.addAttribute("members", receipt.getReceiptId());
            return "redirect:allocation";
        }else {
            return "redirect:receipt";
        }
    }

}
