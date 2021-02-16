package com.chama.app.repository;

import com.chama.app.models.MemberContribution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContributionRepo extends CrudRepository<MemberContribution, Integer> {

    @Query(nativeQuery = true,
        value = "select * from mc_member_contribution where ui_id_fk = :id"
    )
    List<MemberContribution> findAllByMember(@Param("id") int userIntegrationsId);
}
