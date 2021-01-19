package com.chama.app.services;

import com.chama.app.models.Loan;
import com.chama.app.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    LoanRepo loanRepo;

    public void addLoan(Loan loan) {
        loanRepo.save(loan);
    }

    public List<Loan> getLoanRequests(int chamaId) {
        List<Loan> loans = (List<Loan>) loanRepo.findAll();
        List<Loan> loanList = new ArrayList<>();
        for(Loan loan : loans){
            if(loan.getMember().getChama().getChamaId() == chamaId){
                loanList.add(loan);
            }
        }
        return loanList;
    }

    public void denyLoan(int loanId) {
        loanRepo.deleteById(loanId);
    }

    public Loan getLoan(int loanId) {
        return loanRepo.findById(loanId).get();
    }

    public Loan getQueuedLoan(int memberId) {
        return loanRepo.findByMember(memberId);
    }
}
