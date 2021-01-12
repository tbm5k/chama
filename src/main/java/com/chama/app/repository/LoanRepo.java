package com.chama.app.repository;

import com.chama.app.models.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepo extends CrudRepository<Loan, Integer> {


}
