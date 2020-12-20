package com.chama.app.services;

import com.chama.app.models.UserRoles;
import com.chama.app.repository.UserRolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService {

    @Autowired
    UserRolesRepo userRolesRepo;

    public void addUserRole(UserRoles userRole) {
        userRolesRepo.save(userRole);
    }

    public UserRoles getUserRoleObject(int userForeignKey) {
        return userRolesRepo.findByUserForeignKey(userForeignKey);
    }
}
