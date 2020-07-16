package com.chama.app.repository;

import com.chama.app.models.Chama;
import org.springframework.data.repository.CrudRepository;

public interface ChamaRepo extends CrudRepository<Chama, Integer> {

    Chama findByName(String name);

}
