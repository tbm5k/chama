package com.chama.app.repository.multipledata;

import com.chama.app.models.multipledata.ExcelAllocation;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ExcelAllocationRepo extends CrudRepository<ExcelAllocation, Integer> {
    Iterable<ExcelAllocation> findAllByChamaId(int chamaId);

    @Transactional
    void deleteByChamaId(int chamaId);
}
