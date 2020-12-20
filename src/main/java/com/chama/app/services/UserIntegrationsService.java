package com.chama.app.services;

import com.chama.app.models.*;
import com.chama.app.repository.UserIntegrationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserIntegrationsService {

    @Autowired
    UserIntegrationsRepo integrationsRepo;
    @Autowired
    ChamaService chamaService;
    @Autowired
    UserService userService;
    @Autowired
    UserRolesService userRolesService;
    @Autowired
    RolesService rolesService;

    public List<UserIntegrations> getChamaMembers(int chamaId) {
        List<UserIntegrations> members = new ArrayList<>();
        integrationsRepo.findByChamaId(chamaId).forEach(members::add);
        return members;
    }

    public void addNewUser(Invite invite) {
        UserIntegrations userIntegrations = new UserIntegrations();

        userIntegrations.setChama(chamaService.getChamaObject(invite.getChamaForeignKey()));
        userIntegrations.setUser(userService.getUserObject(invite.getUserForeignKey()));

        UserRoles userRole = new UserRoles();

        //fetching the user-role object to use it's id as for the user-roles relationship
        Roles role = rolesService.getRoleId("USER");
        userRole.setUserRoleId(role.getRoleId());

        //save the new user-role relationship
        userRolesService.addUserRole(userRole);

        userIntegrations.setUserRoles(userRolesService.getUserRoleObject(invite.getUserForeignKey()));

        integrationsRepo.save(userIntegrations);
    }
}
