package com.chama.app.services;

import com.chama.app.models.Invite;
import com.chama.app.repository.InviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    InviteRepo inviteRepo;

    public void addNewInvite(Invite invite){
        inviteRepo.save(invite);
    }
}
