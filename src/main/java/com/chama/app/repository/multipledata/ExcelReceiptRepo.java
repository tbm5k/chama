package com.chama.app.repository.multipledata;

import com.chama.app.models.multipledata.ExcelReceipt;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ExcelReceiptRepo extends CrudRepository<ExcelReceipt, Integer> {

    Iterable<ExcelReceipt> findAllByChamaId(int chamaId);

    @Transactional
    void deleteByChamaId(int chamaId);
}
