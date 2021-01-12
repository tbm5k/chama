package com.chama.app.services;

import com.chama.app.models.Loan;
import com.chama.app.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    LoanRepo loanRepo;

    public void addLoan(Loan loan) {
        loanRepo.save(loan);
    }
}
