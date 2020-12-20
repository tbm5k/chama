package com.chama.app.repository;

import com.chama.app.models.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepo extends CrudRepository<UserRoles, Integer> {
    UserRoles findByUserForeignKey(int userForeignKey);
}
