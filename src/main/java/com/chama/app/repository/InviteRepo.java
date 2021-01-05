package com.chama.app.repository;

import com.chama.app.models.Invite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InviteRepo extends CrudRepository<Invite, Integer> {

    //custom method to find invites for a specific user id
    Iterable<Invite> findByUserForeignKey(Integer foreignKey);

    @Transactional
    void deleteInviteByUserForeignKeyAndAndChamaForeignKey(int userId, int chamaId);
}
