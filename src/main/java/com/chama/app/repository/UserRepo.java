package com.chama.app.repository;

import com.chama.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUserId(int id);
}
