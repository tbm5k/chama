package com.chama.app.controllers;

import com.chama.app.models.Invite;
import com.chama.app.repository.UserRepo;
import com.chama.app.services.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InviteController {

    @Autowired
    InviteService inviteService;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/inviteUser")
    public String addInvite(Invite invite, Model model){

        //checking if the user exists before sending an invite
        if(userRepo.findById(invite.getUserForeignKey()).isPresent()){
            inviteService.addNewInvite(invite);
            model.addAttribute("success", "User invited");
        }else{
            model.addAttribute("error","User does not exist");
        }

        return "fragments/chama/chama-dashboard";
    }
}
