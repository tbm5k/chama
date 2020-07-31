package com.chama.app.repository;

import com.chama.app.models.Invite;
import org.springframework.data.repository.CrudRepository;

public interface InviteRepo extends CrudRepository<Invite, Integer> {

    //custom method to find invites for a specific user id
    Iterable<Invite> findByUserForeignKey(Integer foreignKey);

}
