package com.chama.app.services;

import com.chama.app.repository.GuarantorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuarantorService {

    @Autowired
    GuarantorRepo guarantorRepo;

}
