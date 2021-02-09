package com.chama.app.controllers;

import com.chama.app.models.Invite;
import com.chama.app.services.InviteService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    InviteService inviteService;
    @Autowired
    UserIntegrationsService userIntegrationsService;

    //the user dashboard endpoint gets the invites of a specified user and renders them
    @GetMapping("/userDashboard")
    public String getDashboard(Model model){
        int userId = 1; //fetch user id from the session
        model.addAttribute("invites", inviteService.findAllInvites(userId));
        return "fragments/user/user-dashboard";
    }

    @PostMapping("/acceptInvite")
    public String acceptInvite(@ModelAttribute("inv")Invite invite){
        userIntegrationsService.addNewUser(invite);
        return "redirect:userDashboard";
    }

    @GetMapping("/makeContribution")
    public String makeContribution(){
        return "fragments/contribution/contribution";
    }
}
