package com.chama.app.services.multipledata;

import com.chama.app.excel.Excel;
import com.chama.app.models.Receipt;
import com.chama.app.models.UserIntegrations;
import com.chama.app.models.multipledata.ExcelReceipt;
import com.chama.app.repository.ReceiptRepo;
import com.chama.app.repository.UserIntegrationsRepo;
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
    @Autowired
    UserIntegrationsRepo userIntegrationsRepo;
    @Autowired
    ReceiptRepo repo;

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

    public void validateRecords(int chamaId){

        List<ExcelReceipt> receipts = (List<ExcelReceipt>) receiptRepo.findAllByChamaId(chamaId);

        for(ExcelReceipt receipt: receipts){

            List<UserIntegrations> users = (List<UserIntegrations>) userIntegrationsRepo.findByUser(receipt.getUserId());

            Receipt newReceipt = new Receipt();
            if(!repo.existsReceiptsByReceiptNumber(receipt.getReceiptNumber()) && users.size() > 0){

                newReceipt.setMemberId(receipt.getUserId());
                newReceipt.setReceiptNumber(receipt.getReceiptNumber());
                newReceipt.setReceiptAmount(receipt.getReceiptAmount());
                newReceipt.setReceiptDate(receipt.getReceiptDate());
                newReceipt.setPaymentMode(receipt.getPaymentMode());
                newReceipt.setPaymentDescription(receipt.getPaymentDescription());
                newReceipt.setContributionType(receipt.getContributionType());
                newReceipt.setReceiptType(receipt.getReceiptType());

                repo.save(newReceipt);
            }
        }
    }

    public List<ExcelReceipt> getChamaReceipts(int chamaId) {
        return (List<ExcelReceipt>) receiptRepo.findAllByChamaId(chamaId);
    }

    public void clearRecords(int chamaId) {
        receiptRepo.deleteByChamaId(chamaId);
    }
}
