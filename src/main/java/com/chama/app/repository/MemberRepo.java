package com.chama.app.repository;

import com.chama.app.models.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member, Integer> {

    Iterable<Member> findByChamaForeignKey(int chamaId);

}
