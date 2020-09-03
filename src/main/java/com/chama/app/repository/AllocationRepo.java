package com.chama.app.repository;

import com.chama.app.models.Allocation;
import org.springframework.data.repository.CrudRepository;

public interface AllocationRepo extends CrudRepository<Allocation, Integer> {
}
