package com.chama.app.repository;

import com.chama.app.models.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepo extends CrudRepository<Receipt, Integer> {
    boolean findByReceiptNumber(String receiptNumber);
}
