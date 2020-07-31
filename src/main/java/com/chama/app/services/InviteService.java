package com.chama.app.services;

import com.chama.app.models.Invite;
import com.chama.app.repository.InviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InviteService {

    @Autowired
    InviteRepo inviteRepo;

    public void addNewInvite(Invite invite){
        inviteRepo.save(invite);
    }

    public List<Invite> findAllInvites() {
        List<Invite> inviteList = new ArrayList<>();
        inviteRepo.findAll().forEach(inviteList::add);
        return inviteList;
    }
}
