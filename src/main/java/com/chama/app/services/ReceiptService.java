package com.chama.app.services;

import com.chama.app.excel.Excel;
import com.chama.app.models.Receipt;
import com.chama.app.repository.ReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void addReceipts(MultipartFile multipartFile) {
        try {
            List<Receipt> receipts = Excel.excelFile(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
