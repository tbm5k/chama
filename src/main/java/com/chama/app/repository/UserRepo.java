package com.chama.app.repository;

import com.chama.app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {

}
