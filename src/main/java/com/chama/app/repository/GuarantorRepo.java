package com.chama.app.repository;

import com.chama.app.models.Guarantor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuarantorRepo extends CrudRepository<Guarantor, Integer> {
}
