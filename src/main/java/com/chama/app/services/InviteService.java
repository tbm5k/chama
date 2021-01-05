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

    //returns a list of invites of a user
    public List<Invite> findAllInvites(int userId) {
        List<Invite> inviteList = new ArrayList<>();
        inviteRepo.findByUserForeignKey(userId).forEach(inviteList::add);
        return inviteList;
    }

    public void clearInvite(int userId, int chamaId) {
        inviteRepo.deleteInviteByUserForeignKeyAndAndChamaForeignKey(userId, chamaId);
    }
}
