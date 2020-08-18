package com.chama.app.services;

import com.chama.app.models.Receipt;
import com.chama.app.repository.ReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepo receiptRepo;

    public void addNewReceipt(Receipt receipt) {
        receiptRepo.save(receipt);
    }
}
