package com.chama.app.services.multipledata;

import com.chama.app.excel.Excel;
import com.chama.app.models.Allocation;
import com.chama.app.models.Chama;
import com.chama.app.models.Sequence;
import com.chama.app.models.UserIntegrations;
import com.chama.app.models.multipledata.ExcelAllocation;
import com.chama.app.repository.AllocationRepo;
import com.chama.app.repository.SequenceRepo;
import com.chama.app.repository.UserIntegrationsRepo;
import com.chama.app.repository.multipledata.ExcelAllocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelAllocationService {

    @Autowired
    ExcelAllocationRepo excelAllocationRepo;
    @Autowired
    UserIntegrationsRepo userIntegrationsRepo;
    @Autowired
    SequenceRepo sequenceRepo;
    @Autowired
    AllocationRepo allocationRepo;

    public void addAllocations(MultipartFile multipartFile) throws IOException {

        List<ExcelAllocation> allocations = Excel.allocationFile(multipartFile.getInputStream());

        for(ExcelAllocation allocation : allocations){
            allocation.setChamaId(1);//dynamically set the chama id from the session
        }

        excelAllocationRepo.saveAll(allocations);

    }

    public void validateRecords(int chamaId) {

        List<ExcelAllocation> excelAllocations = (List<ExcelAllocation>) excelAllocationRepo.findAllByChamaId(chamaId);
        Sequence sequence = sequenceRepo.findByChamaForeignKey(chamaId);

        for(ExcelAllocation excelAllocation: excelAllocations){
            List<UserIntegrations> users = (List<UserIntegrations>) userIntegrationsRepo.findByUser(excelAllocation.getMemberId());

            if(users.size() > 0 && excelAllocation.getReceiptNumber().startsWith(sequence.getPrefix())){
                Allocation allocation = new Allocation();

                allocation.setAmount(excelAllocation.getAllocationAmount());
                allocation.setReceiptDate(excelAllocation.getAllocationDate());
                allocation.setReceiptNumber(excelAllocation.getReceiptNumber());
                allocation.setAllocationPeriod(excelAllocation.getAllocationPeriod());
                allocation.setMemberId(excelAllocation.getMemberId());

                allocationRepo.save(allocation);
            }
        }

    }
}
