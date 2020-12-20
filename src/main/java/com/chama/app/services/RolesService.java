package com.chama.app.services;

import com.chama.app.models.Roles;
import com.chama.app.repository.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    RolesRepo rolesRepo;

    public Roles getRoleId(String roleName) {
        return rolesRepo.findByRoleName(roleName);
    }
}
