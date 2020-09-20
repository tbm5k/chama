package com.chama.app.services.multipledata;

import com.chama.app.excel.Excel;
import com.chama.app.models.Receipt;
import com.chama.app.models.multipledata.ExcelReceipt;
import com.chama.app.repository.multipledata.ExcelReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelReceiptService {

    @Autowired
    ExcelReceiptRepo receiptRepo;

    public void addReceipts(MultipartFile multipartFile) {
        try {
            List<ExcelReceipt> receipts = Excel.receiptFile(multipartFile.getInputStream());
            for(ExcelReceipt receipt : receipts){
                receipt.setChamaId(1);//dynamically set the chama id(from the session)
            }
            receiptRepo.saveAll(receipts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
