package com.chama.app.repository;

import com.chama.app.models.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoanRepo extends CrudRepository<Loan, Integer> {

    @Query(nativeQuery = true,
            value = "select * from lo_loan where ui_id_fk = :memberId"
    )
    Loan findByMember(@Param("memberId") int memberId);
}
