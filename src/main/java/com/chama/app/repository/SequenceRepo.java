package com.chama.app.repository;

import com.chama.app.models.Sequence;
import org.springframework.data.repository.CrudRepository;

public interface SequenceRepo extends CrudRepository<Sequence, Integer> {
    Sequence findByChamaForeignKey(int chamaId);
}
