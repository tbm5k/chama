package com.chama.app.services;

import com.chama.app.models.Guarantor;
import com.chama.app.repository.GuarantorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GuarantorService {

    @Autowired
    GuarantorRepo guarantorRepo;

    public List<Guarantor> getGuarantors(int loanId) {
        return (List<Guarantor>) guarantorRepo.findAllById(Collections.singleton(loanId));
    }
}
