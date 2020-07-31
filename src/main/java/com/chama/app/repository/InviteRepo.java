package com.chama.app.repository;

import com.chama.app.models.Invite;
import org.springframework.data.repository.CrudRepository;

public interface InviteRepo extends CrudRepository<Invite, Integer> {

    Iterable<Invite> findByUserForeignKey(Integer foreignKey);

}
