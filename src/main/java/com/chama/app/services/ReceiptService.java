package com.chama.app.services;

import com.chama.app.models.Receipt;
import com.chama.app.repository.ReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepo receiptRepo;

    public void addNewReceipt(Receipt receipt) {
        receiptRepo.save(receipt);
    }

    public int findTotal() {
        List<Receipt> receiptList = new ArrayList<>();
        receiptRepo.findAll().forEach(receiptList::add);
        return receiptList.size();
    }

    public Receipt getLastReceipt() {
        List<Receipt> receipts = (List<Receipt>) receiptRepo.findAll();
        return receipts.get(receipts.size() -1);
    }
}
