package com.chama.app.repository;

import com.chama.app.models.MemberContribution;
import org.springframework.data.repository.CrudRepository;

public interface ContributionRepo extends CrudRepository<MemberContribution, Integer> {
}
