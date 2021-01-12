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
    @Autowired
    InviteService inviteService;

    public List<UserIntegrations> getChamaMembers(int chamaId) {
        List<UserIntegrations> members = new ArrayList<>();
        integrationsRepo.findByChamaId(chamaId).forEach(members::add);
        return members;
    }

    public void addNewUser(Invite invite) {

        //creating a user role on relationship on acceptance of chama invitation
        UserRoles userRole = new UserRoles();

        Roles role = rolesService.getRoleObject("USER");
        User user = userService.getUserObject(invite.getUserForeignKey());

        userRole.setUserForeignKey(user);
        userRole.setRoleForeignKey(role);

        //save the new user-role relationship
        userRolesService.addUserRole(userRole);

        //creating a relationship between the user and the chama and setting his/her role in the chama
        UserIntegrations userIntegrations = new UserIntegrations();//initializing a ui obj

        userIntegrations.setChama(chamaService.getChamaObject(invite.getChamaForeignKey()));
        userIntegrations.setUser(userService.getUserObject(invite.getUserForeignKey()));
        userIntegrations.setUserRoles(userRolesService.getUserRoleObject(invite.getUserForeignKey()));

        //saving the user integrations object
        integrationsRepo.save(userIntegrations);

        //deleting the invitation
        inviteService.clearInvite(invite.getUserForeignKey(), invite.getChamaForeignKey());
    }

    public UserIntegrations getChamaMember(int userId, int chamaId) {
        return integrationsRepo.findByUserAndChama(userId, chamaId);
    }
}
