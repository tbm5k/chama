package com.chama.app.services;

import com.chama.app.models.MemberContribution;
import com.chama.app.repository.ContributionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionService {

    @Autowired
    ContributionRepo contributionRepo;

    public void makeContribution(MemberContribution memberContribution) {
        contributionRepo.save(memberContribution);
    }
}
