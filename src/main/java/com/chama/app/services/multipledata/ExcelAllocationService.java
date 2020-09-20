package com.chama.app.services.multipledata;

import com.chama.app.excel.Excel;
import com.chama.app.models.multipledata.ExcelAllocation;
import com.chama.app.repository.multipledata.ExcelAllocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelAllocationService {

    @Autowired
    ExcelAllocationRepo allocationRepo;

    public void addAllocations(MultipartFile multipartFile) throws IOException {

        List<ExcelAllocation> allocations = Excel.allocationFile(multipartFile.getInputStream());

        for(ExcelAllocation allocation : allocations){
            allocation.setChamaId(1);//dynamically set the chama id from the session
        }

        allocationRepo.saveAll(allocations);

    }
}
