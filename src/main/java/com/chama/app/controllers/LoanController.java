package com.chama.app.controllers;

import com.chama.app.models.Loan;
import com.chama.app.models.UserIntegrations;
import com.chama.app.services.LoanService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    UserIntegrationsService userIntegrationsService;

    @GetMapping("/requestLoan")
    public String getLoanPage(Model model){
        model.addAttribute("loan", new Loan());
        return "fragments/Loan/request-loan";
    }

    @PostMapping("/requestLoan")
    public String requestLoan(Loan loan){
        loan.setUuid();
        //fetch member from the session;
        loan.setMember(userIntegrationsService.getChamaMember(1, 2));
        loanService.addLoan(loan);
        return "redirect:requestLoan";
    }

    @GetMapping("/denyLoanRequest/{loanId}")
    public String denyLoan(@PathVariable("loanId") int loanId){
        loanService.denyLoan(loanId);
        return "redirect:/chamaDashboard";
    }

    @GetMapping("/acceptLoan")
    public String acceptLoan(){
        return "redirect:chamaDashboard";
    }
}
