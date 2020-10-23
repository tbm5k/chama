package com.chama.app.controllers;

import com.chama.app.services.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    InviteService inviteService;

    //the user dashboard endpoint gets the invites of a specified user and renders them
    @GetMapping("/userDashboard")
    public String getDashboard(Model model){
        model.addAttribute("invites", inviteService.findAllInvites(1));
        return "fragments/user/user-dashboard";
    }
}
