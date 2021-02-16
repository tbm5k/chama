package com.chama.app.controllers;

import com.chama.app.models.*;
import com.chama.app.services.*;
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
    @Autowired
    ContributionService contributionService;

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

        if(!receipt.getContributionType().equals("Contributions")){
            receipt.setMember(null);
        }

        //setting the receipt number
        int num = receiptService.findTotal();
        String prefix = sequenceService.findChamaSequence(chamaId);
        String receiptNumber = prefix + "/" + num + "/" + calendar.get(Calendar.YEAR);
        receipt.setReceiptNumber(receiptNumber);//saving the receipt number

        receiptService.addNewReceipt(receipt);

        /*
        If the payment description states monthly contribution, update the member
        contribution setting the receipt number
         */
        if(receipt.getPaymentDescription().equals("Monthly contribution")){
            MemberContribution memberContribution = contributionService.getMemberContribution(receipt.getMember().getUserIntegrationsId());
            memberContribution.setReceiptNumber(receipt.getReceiptNumber());
            memberContribution.setReceiptDate(receipt.getReceiptDate());
            memberContribution.setConfirm(true);

            contributionService.updateContribution(memberContribution);

            return "redirect:chamaDashboard";
        }

        if(receipt.getPaymentDescription().equals("Loan")){
            Loan loan = loanService.getQueuedLoan(receipt.getMember().getUserIntegrationsId());

            Allocation allocation = new Allocation();
            allocation.setAmount(receipt.getReceiptAmount());
            allocation.setReceiptDate(receipt.getReceiptDate());
            allocation.setReceiptNumber(receipt.getReceiptNumber());
            allocation.setAllocationPeriod(loan.getLoanPeriod());

            loanService.clearLoan(loan.getLoanId());

            model.addAttribute("allocation", allocation);
            model.addAttribute("members", receipt.getMember());
            return "fragments/allocation/allocation";
        }else {
            return "redirect:receipt";
        }
    }

}
