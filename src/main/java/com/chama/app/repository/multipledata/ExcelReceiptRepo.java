package com.chama.app.repository.multipledata;

import com.chama.app.models.multipledata.ExcelReceipt;
import org.springframework.data.repository.CrudRepository;

public interface ExcelReceiptRepo extends CrudRepository<ExcelReceipt, Integer> {

    Iterable<ExcelReceipt> findAllByChamaId(int chamaId);

    void deleteByChamaId(int chamaId);
}
