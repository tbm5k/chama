package com.chama.app.controllers;

import com.chama.app.models.Allocation;
import com.chama.app.models.Loan;
import com.chama.app.models.MemberContribution;
import com.chama.app.models.Receipt;
import com.chama.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    UserIntegrationsService userIntegrationsService;
    @Autowired
    AllocationService allocationService;
    @Autowired
    ReceiptService receiptService;
    @Autowired
    ContributionService contributionService;

    @GetMapping("/requestLoan")
    public String getLoanPage(Model model){
        model.addAttribute("loan", new Loan());
        return "fragments/Loan/request-loan";
    }

    @PostMapping("/requestLoan")
    public String requestLoan(Loan loan, Model model){
        int memberId = 11;
        int totalAmount = 0;
        //fetch member from the session;
        loan.setMember(userIntegrationsService.getChamaMember(1, 2));

        /*
        check whether the user is eligible for a loan
         If the requested loan is more than the user's chama worth, suggest to pick a guarantor guarantor
         */

        try {
            List<MemberContribution> contributionList = contributionService.getAllMemberContributions(memberId);

            for(MemberContribution contribution : contributionList){
                totalAmount += contribution.getAmount();
            }

            if(loan.getAmount() > totalAmount){
                model.addAttribute("message", "You have " + totalAmount + " which is low to acquire a loan. Request for a guarantor");
                return "fragments/Loan/request-loan";
            }else{
                model.addAttribute("message", "Loan requested");
                loanService.addLoan(loan);
            }
        }catch (Exception e){
            model.addAttribute("message","Error while validating loan");
            return "fragments/Loan/request-loan";
        }

        return "redirect:requestLoan";
    }

    @GetMapping("/denyLoanRequest/{loanId}")
    public String denyLoan(@PathVariable("loanId") int loanId){
        loanService.denyLoan(loanId);
        return "redirect:/chamaDashboard";
    }

    @PostMapping("/acceptLoan")
    public String acceptLoan(Loan loan, Model model){
        Loan dbLoan = loanService.getLoan(loan.getLoanId());

        Receipt receipt = new Receipt();
        receipt.setReceiptAmount(dbLoan.getAmount());

        model.addAttribute("receipt", receipt);
        model.addAttribute("members", dbLoan.getMember());
        return "fragments/receipt/receipt";
    }
}
