package com.chama.app.repository;

import com.chama.app.models.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepo extends CrudRepository<Roles, Integer> {
    Roles findByRoleName(String roleName);
}
